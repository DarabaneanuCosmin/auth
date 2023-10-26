package com.app.auth.requests;

import com.app.auth.models.entities.Customer;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class CustomerRequest {
    @Column(unique = true)
    @NotBlank(message = "Email Address must be >=1 characters")
    @Size(min = 1, max = 255)
    private String emailAddress;
    @NotBlank(message = "First Name must be between 1 to 100 characters")
    @Size(min = 1, max = 255)
    private String firstName;

    @NotBlank(message = "Last Name must be between 1 to 100 characters")
    @Size(min = 1, max = 255)
    private String lastName;

    @NotBlank(message = "Password must have more than 8 characters")
    @Size(min = 8, max = 200)
    private String password;

    @NotBlank(message = "Password must have more than 8 characters")
    @Size(min = 8, max = 200)
    private String retryPassword;

    public Customer convertToEntity()
    {
        return Customer.builder()
                .emailAddress(this.emailAddress)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }
}
