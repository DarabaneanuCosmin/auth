package com.app.auth.service;

import com.app.auth.models.dtos.CustomerDto;
import com.app.auth.models.entities.Customer;
import com.app.auth.repositories.CustomerRepository;
import com.app.auth.requests.CustomerRequest;
import com.app.auth.security.JwtSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtSecurity jwtSecurity;

    private final Random random;

    public AuthenticationService(Random random) {
        this.random = random;
    }
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDto save(CustomerRequest request) {
        Customer customer = this.customerRepository.findByEmailAddress(request.getEmailAddress());
        if (customer != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email address already exists");
        }
        Customer entity = request.convertToEntity();
        entity.setPassword(this.passwordEncoder.encode(request.getPassword()));
        entity.setUsername(request.getFirstName() + '.' + request.getLastName() + this.random.nextInt());

        return CustomerDto.convertToDto(this.customerRepository.save(entity));
    }

    public ResponseEntity<?> authenticate(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String jwt = this.jwtSecurity.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);

        return ResponseEntity.ok(response);
    }
}
