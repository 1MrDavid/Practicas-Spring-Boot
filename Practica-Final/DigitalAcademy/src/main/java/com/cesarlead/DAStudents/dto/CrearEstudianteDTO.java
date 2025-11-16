package com.cesarlead.DAStudents.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.cesarlead.DAStudents.config.AppConstant;
import jakarta.validation.constraints.Size;

public record CrearEstudianteDTO(
        @NotBlank(message = AppConstant.NOMBRE_NOT_BLANK)
        @NotNull(message = AppConstant.NOMBRE_REQUIRED)
        @Size(min = 2, max = 100)
        String nombre,

        @NotBlank(message = AppConstant.APELLIDO_NOT_BLANK)
        @NotNull(message = AppConstant.APELLIDO_REQUIRED)
        @Size(min = 2, max = 100)
        String apellido,

        @Email(message = AppConstant.EMAIL_INVALID)
        @NotBlank(message = AppConstant.EMAIL_NOT_BLANK)
        String email
) {

}
