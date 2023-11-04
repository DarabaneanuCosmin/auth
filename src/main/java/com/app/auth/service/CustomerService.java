package com.app.auth.service;

import com.app.auth.models.dtos.CustomerDto;
import com.app.auth.models.entities.Customer;
import com.app.auth.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<?> getByUsername(String username){
        Customer customer = this.customerRepository.findByUsername(username);
        if(customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CustomerDto.convertToDto(customer));
    }
}
