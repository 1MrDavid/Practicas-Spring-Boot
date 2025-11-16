package com.cesarlead.DAProgress.client;

import com.cesarlead.DAProgress.exception.ExternalServiceException;
import com.cesarlead.DAProgress.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EnrollmentServiceClient {

    private final WebClient webClient;

    public EnrollmentServiceClient(WebClient.Builder webClientBuilder,
                                   @Value("${services.enrollment.url}") String enrollmentServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(enrollmentServiceUrl).build();
    }

    public Mono<Void> validateEnrollment(Long cursoId, Long estudianteId) {
        return webClient.get()
                .uri("/exist/{cursoId}/{estudianteId}", cursoId, estudianteId)
                .retrieve()
                // Si el servicio remoto responde 404 → inscripción NO existe
                .onStatus(status -> status == HttpStatus.NOT_FOUND,
                        response -> Mono.error(
                                new ResourceNotFoundException("La inscripción del estudiante "
                                        + estudianteId + " al curso " + cursoId + " no existe")
                        )
                )
                // Manejo de errores 4xx / 5xx
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new ExternalServiceException("Error en servicio de clientes: " + errorBody))))
                .bodyToMono(Void.class); // No esperamos cuerpo
    }
}
