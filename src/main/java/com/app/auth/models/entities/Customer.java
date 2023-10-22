package com.app.auth.models.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
}
