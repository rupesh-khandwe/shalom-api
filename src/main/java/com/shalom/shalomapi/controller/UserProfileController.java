package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.Config.JwtGeneratorImpl;
import com.shalom.shalomapi.dto.AuthRequestDTO;
import com.shalom.shalomapi.dto.JwtResponseDTO;
import com.shalom.shalomapi.dto.RefreshTokenRequestDTO;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.JwtService;
import com.shalom.shalomapi.service.RefreshTokenService;
import com.shalom.shalomapi.service.UserProfileService;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/userProfile/v1/")
@CrossOrigin
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody UserProfile userProfile) throws Exception {
        try{
            userProfileService.saveUserProfile(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getUserName(), authenticationReq.getPassword()));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if(authentication.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                final UserDetails userDetails = userProfileService.loadUserByUsername(authRequestDTO.getUsername());
                CustomUser user = null;
                SecurityContext securityContext = SecurityContextHolder.getContext();
                if (null != securityContext.getAuthentication()) {
                    user = (CustomUser) userDetails;
                }

                return new ResponseEntity<>(JwtResponseDTO.builder()
                        .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                        .refreshToken(refreshToken.getToken())
                        .userId(user.getUserId())
                        .userName(WordUtils.capitalizeFully(user.getUserFirstName()) + " " + WordUtils.capitalizeFully(user.getUserLastName()))
                        .build(), HttpStatus.OK);
            }
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        } catch(Exception ex) {
            // check if exception is due to ExpiredJwtException
            if (ex.getMessage().contains("io.jsonwebtoken.ExpiredJwtException")) {
                // Refresh Token
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Token is expired");
            }
        }

       // final String jwt = jwtService.generateToken(userDetails.getUsername());
        //JwtResponse res = new JwtResponse(jwt, user.getUserId(), WordUtils.capitalizeFully(user.getUserFirstName()), WordUtils.capitalizeFully(user.getUserLastName()));
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUserName());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }
}
