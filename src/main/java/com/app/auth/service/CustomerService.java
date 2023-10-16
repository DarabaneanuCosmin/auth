package com.app.auth.service;

import com.app.auth.models.requests.CustomerRequest;
import com.app.auth.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO save(CustomerRequest request){
        
    }
}
