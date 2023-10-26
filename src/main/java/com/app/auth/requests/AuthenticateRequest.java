package com.app.auth.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthenticateRequest {
    @NotBlank(message = "Email Address must be >=1 characters")
    @Size(min = 1, max = 255)
    public String email_address;

    @NotBlank(message = "Password must have more than 8 characters")
    @Size(min = 8, max = 200)
    public String password;
}
