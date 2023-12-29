package com.shalom.shalomapi.controller;

import com.shalom.shalomapi.Config.JwtGeneratorImpl;
import com.shalom.shalomapi.Config.JwtUtil;
import com.shalom.shalomapi.model.AuthenticationRequest;
import com.shalom.shalomapi.model.JwtRequest;
import com.shalom.shalomapi.model.JwtResponse;
import com.shalom.shalomapi.model.UserProfile;
import com.shalom.shalomapi.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationReq, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getUserName(), authenticationReq.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userProfileService.loadUserByUsername(authenticationReq.getUserName());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new ResponseEntity<>(jwt, HttpStatus.OK);

    }

 /*   @PostMapping("/authenticate")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            if(authenticationRequest.getUserName() == null || authenticationRequest.getPassword() == null) {
                throw new UsernameNotFoundException("UserName or Password is Empty");
            }

            authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());

            final UserDetails userDetails = userProfileService
                    .loadUserByUsername(authenticationRequest.getUserName());

  *//*          UserProfile userData= userProfileService.getUserByNameAndPassword(userProfile.getUserName(), userProfile.getPassword());
            if(userData == null){
                throw new UsernameNotFoundException("UserName or Password is Invalid");
            }*//*
            return new ResponseEntity<>(new JwtResponse(jwtGenerator.generateToken(userDetails)), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }*/
}
