package com.cesarlead.DACourses.client;

import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.exception.ServiceUnavailableException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

  private final ErrorDecoder defaultErrorDecoder = new Default();

  @Override
  public Exception decode(String methodKey, Response response) {

    // Error de busqueda
    if (methodKey.contains("findById") && response.status() == 404) {
      return new ResourceNotFoundException("Estudiante no encontrado. Meotodo invocado: " + methodKey);
    }

    // Errores del cliente
    if (response.status() >= 400 && response.status() <= 499) {
      return new ResourceNotFoundException("Client error: " + response.status() + "-" + response.reason());
    }

    // Errores del servidor
    if (response.status() >= 500 && response.status() <= 599) {
      return new ServiceUnavailableException("Service unavailable. Status: " + response.status());
    }

    // Cualquier otro error (generico)
    return defaultErrorDecoder.decode(methodKey, response);
  }
}
