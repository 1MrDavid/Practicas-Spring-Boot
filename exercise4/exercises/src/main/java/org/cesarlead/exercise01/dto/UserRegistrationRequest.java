package org.cesarlead.exercise01.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(
        @NotNull(message = "username is mandatory")
        @NotBlank(message = "username is mandatory")
        String username,

        @Email
        String email,

        @NotNull(message = "password is mandatory")
        @NotBlank(message = "password is mandatory")
        String password,

        @NotNull(message = "age is mandatory")
        @NotBlank(message = "age is mandatory")
        Integer age
) {
}
