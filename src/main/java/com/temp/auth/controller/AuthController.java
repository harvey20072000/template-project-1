package com.temp.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temp.api.request.LoginReqBody;
import com.temp.api.response.LoginResBody;
import com.temp.util.JwtTokenUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @PostMapping("/login")
    public ResponseEntity<LoginResBody> login(@RequestBody LoginReqBody loginReqBody) {
        this.doAuthenticate(loginReqBody.getUserName(), loginReqBody.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReqBody.getUserName());
        String token = this.jwtTokenUtils.generateToken(userDetails);
        LoginResBody response = new LoginResBody();
        response.setToken(token);
        response.setUserName(userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password!");
        }
    }
}