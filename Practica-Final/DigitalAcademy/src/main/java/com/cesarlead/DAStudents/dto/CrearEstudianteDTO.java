package com.cesarlead.DAStudents.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearEstudianteDTO(
        @NotBlank(message = "El nombre no puede estar en blanco")
        @NotNull(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido no puede estar en blanco")
        @NotNull(message = "El apellido es obligatorio")
        String apellido,

        @Email(message = "Debe ser una direccion de email valida")
        @NotBlank(message = "El email no puede estar en blanco")
        String email
) {

}
