package com.employeeService.controller;

import com.employeeService.model.JwtRequest;
import com.employeeService.model.JwtResponse;
import com.employeeService.services.MyUserDetailService;
import com.employeeService.utility.JWTUtility;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private JWTUtility jwtUtility;


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService userDetailService;

    @GetMapping("/")
    public String home() {
        return "welcome to daily code";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            System.out.println("inside try method");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

        } catch (BadCredentialsException e) {
            System.out.println("bad credentials");
            throw new Exception("INVALID CREDENTIALS", e);
        }


        final UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        System.out.println("token generated");
        return new JwtResponse(token);
    }
}
