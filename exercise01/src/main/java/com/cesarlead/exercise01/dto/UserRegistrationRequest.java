package com.cesarlead.exercise01.dto;

import com.cesarlead.exercise01.annotation.ValidPassword;
import com.cesarlead.exercise01.groups.OnCreate;
import com.cesarlead.exercise01.groups.OnUpdate;
import jakarta.validation.*;
import jakarta.validation.constraints.*;

public record UserRegistrationRequest(
        @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Name is mandatory")
        @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "Name is mandatory")
        @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
        String username,

        @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Enter a valid email")
        @Email(groups = {OnCreate.class, OnUpdate.class}, message = "Enter a valid email")
        String email,

        @ValidPassword(groups = {OnCreate.class})
        String password,

        @NotNull(groups = {OnCreate.class}, message = "Age is mandatory")
        @NotBlank(groups = {OnCreate.class}, message = "Age is mandatory")
        @Min(groups = {OnCreate.class}, value = 18, message = "Age should be greater than 18")
        @Max(groups = {OnCreate.class}, value = 100, message = "Age should be less than 100")
        Integer age
) {
}
