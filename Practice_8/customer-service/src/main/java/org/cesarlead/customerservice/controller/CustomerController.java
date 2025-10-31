package org.cesarlead.customerservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.cesarlead.customerservice.dto.CustomerDTO;
import org.cesarlead.customerservice.dto.ErrorDetail;
import org.cesarlead.customerservice.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

import static org.cesarlead.customerservice.config.AppConstant.LOG_ENTERING_METHOD;
import static org.cesarlead.customerservice.config.AppConstant.LOG_USER_CONSULTED;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "administracion-cliente", description = "administracion de cliente")
@Slf4j
public class CustomerController {

    // Legacy, escritura directa a pulmon
    //private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Obtiene cliente por  ID", description = "Cliente debe existir")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion exitosa",
                    content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = CustomerDTO.class)
                            )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Operacion fallida",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)
                    )
            )
    })
    public ResponseEntity<CustomerDTO> getCustomerById(
    @Parameter(
            description = "ID del cliente",
            required = true,
            example = "1234")
            @RequestParam Long id
    )
    {
        log.info(LOG_ENTERING_METHOD, id);

        CustomerDTO response = customerService.findCustomerById(id);

        log.debug("Respuesta recibida: " + response);

        log.info(LOG_USER_CONSULTED, id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
