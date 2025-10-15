package com.cesarlead.exercise01.dto;

import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record UserRegistrationRequest(
        @NotNull(message = "Name is mandatory")
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
        String username,

        @NotNull
        @Email(message = "Enter a valid email")
        String email,

        @NotNull(message = "Password is mandatory")
        String password,

        @NotNull(message = "Age is mandatory")
        @Min(value = 18, message = "Age should be greater than 18")
        @Max(value = 100, message = "Age should be less than 100")
        Integer age
) {
}
