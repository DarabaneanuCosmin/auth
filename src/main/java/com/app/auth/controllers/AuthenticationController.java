package com.app.auth.controllers;

import com.app.auth.requests.CustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @PostMapping("")
    public ResponseEntity<?> signup(@Valid @RequestBody CustomerRequest customer) {
        try {

            return ResponseEntity.ok().body("yes");
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error\n");
        }
    }
}
