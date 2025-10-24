package org.cesarlead.customerservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        String email)
{
}
