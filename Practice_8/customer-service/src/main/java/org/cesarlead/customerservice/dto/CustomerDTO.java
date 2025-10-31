package org.cesarlead.customerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Consulta de cliente")
public record CustomerDTO(
        @Schema(
                description = "ID del cliente",
                example = "1234",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long id,

        @Schema(description = "Primer nombre del cliente")
        String firstName,

        @Schema(description = "Apellido del cliente")
        String lastName,

        @Schema(description = "Correo del cliente")
        String email)
{
}
