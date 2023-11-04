package com.app.auth.models.dtos;

import com.app.auth.models.entities.Customer;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
public class CustomerDto implements Serializable {
    @NotBlank(message = "Email Address must be >=1 characters")
    @Size(min = 1, max = 255)
    private final String emailAddress;
    @NotBlank(message = "Username must not be empty")
    @Size(min = 1)
    private String username;
    @NotBlank(message = "First Name must be between 1 to 100 characters")
    @Size(min = 1, max = 255)
    private final String firstName;
    @NotBlank(message = "Last Name must be between 1 to 100 characters")
    @Size(min = 1, max = 255)
    private final String lastName;

    public static CustomerDto convertToDto(Customer customer) {
        return CustomerDto.builder()
                .emailAddress(customer.getEmailAddress())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}