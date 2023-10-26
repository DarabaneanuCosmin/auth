package com.app.auth.service;

import com.app.auth.models.dtos.CustomerDto;
import com.app.auth.models.entities.Customer;
import com.app.auth.repositories.CustomerRepository;
import com.app.auth.requests.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDto save(CustomerRequest request) {
        Customer customer = this.customerRepository.findByEmailAddress(request.getEmailAddress());
        if (customer != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email address already exists");
        }
        Customer entity = request.convertToEntity();
        entity.setPassword(this.passwordEncoder.encode(request.getPassword()));

        return CustomerDto.convertToDto(this.customerRepository.save(entity));
    }


}
