package com.cesarlead.DAProgress.client;

import com.cesarlead.DAProgress.config.AppConstant;
import com.cesarlead.DAProgress.dto.CursoDTO;
import com.cesarlead.DAProgress.exception.ExternalServiceException;
import com.cesarlead.DAProgress.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CourseServiceClient {
    private final WebClient webClient;

    public CourseServiceClient(WebClient.Builder webClientBuilder,
                                @Value("${services.course.url}") String studentServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(studentServiceUrl).build();
    }

    public Mono<CursoDTO> getCourseByID(Long cursoId) {
        return webClient.get()
                .uri("/{id}", cursoId)
                .retrieve()
                // Manejo de error 404
                .onStatus(status -> status == HttpStatus.NOT_FOUND,
                        response -> Mono.error(new ResourceNotFoundException(AppConstant.LOG_COURSE_NOT_FOUND + cursoId)))
                // Manejo de errores 4xx y 5xx genéricos
                .onStatus(status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new ExternalServiceException("Error en servicio de clientes: " + errorBody))))
                .bodyToMono(CursoDTO.class);
    }
}
