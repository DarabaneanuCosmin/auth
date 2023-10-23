package com.app.auth.controllers;

import com.app.auth.requests.CustomerRequest;
import com.app.auth.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<?> signup(@Valid @RequestBody CustomerRequest customer) {
        try {
            return ResponseEntity.ok().body(this.customerService.save(customer));
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error\n");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody Map<String, Object> userMap) {
       String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userMap.get("username"), userMap.get("password")));
        return ResponseEntity.ok("yes");
    }

}
