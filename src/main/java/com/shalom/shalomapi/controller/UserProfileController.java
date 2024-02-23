package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.Config.JwtGeneratorImpl;
import com.shalom.shalomapi.Config.JwtUtil;
import com.shalom.shalomapi.model.*;
import com.shalom.shalomapi.service.UserProfileService;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
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
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationReq, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getUserName(), authenticationReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userProfileService.loadUserByUsername(authenticationReq.getUserName());

        CustomUser user=null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(null != securityContext.getAuthentication()){
            user = (CustomUser) userDetails;
        }
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        JwtResponse res = new JwtResponse(jwt, user.getUserId(), WordUtils.capitalizeFully(user.getUserFirstName()), WordUtils.capitalizeFully(user.getUserLastName()));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
