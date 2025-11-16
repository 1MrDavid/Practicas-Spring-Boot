package com.cesarlead.DAStudents.config;

import com.cesarlead.DAStudents.config.AppConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Obtener el TraceId de la cabecera o generar uno nuevo
        String traceId = request.getHeader(AppConstant.TRACE_ID_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString();
        }

        try {
            // 2. Poner el TraceId en el MDC
            MDC.put(AppConstant.TRACE_ID_MDC_KEY, traceId);

            // 3. Añadir el TraceId a la respuesta (para que el cliente lo vea)
            response.addHeader(AppConstant.TRACE_ID_HEADER, traceId);

            // Continuar con la cadena de filtros
            filterChain.doFilter(request, response);

        } finally {
            // 4. Limpiar el MDC después de que la petición termine
            MDC.remove(AppConstant.TRACE_ID_MDC_KEY);
        }
    }
}

