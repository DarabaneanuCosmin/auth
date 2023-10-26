package com.app.auth.controllers;

import com.app.auth.requests.AuthenticateRequest;
import com.app.auth.requests.CustomerRequest;
import com.app.auth.security.JwtSecurity;
import com.app.auth.service.CustomUserDetailsService;
import com.app.auth.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtSecurity jwtSecurity;

    @PostMapping("")
    public ResponseEntity<?> signup(@Valid @RequestBody CustomerRequest customer) {
        try {
            return ResponseEntity.ok().body(this.customerService.save(customer));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody AuthenticateRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email_address, request.password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return this.customerService.authenticate();
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email_address);
        String jwt = this.jwtSecurity.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);

        return ResponseEntity.ok(response);
    }

}
