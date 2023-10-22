package com.app.auth.repositories;

import com.app.auth.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmailAddress(String emailAddress);
}
