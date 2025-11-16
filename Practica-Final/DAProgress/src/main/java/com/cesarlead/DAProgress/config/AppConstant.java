package com.cesarlead.DAProgress.config;

public class AppConstant {

    // Mensajes de log
    public static final String LOG_ENTERING_METHOD = "Entrando al método: {}";
    public static final String LOG_EXITING_METHOD = "Saliendo del método: {}";

    // Para LoggingFilter usado para trazabilidad
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";

    public static final String LOG_ENROLLMENT_CREATED = "Inscripcion creada del estudiante con ID: {} para el curso con ID: {}";

    // Mensajes de consulta WebClient
    public static final String LOG_STUDENT_NOT_FOUND = "Estudiante no encontrado: ";
    public static final String LOG_COURSE_NOT_FOUND = "Curso no encontrado: ";
    public static final String MSG_TITULO_NOT_BLANK = "El titulo no puede estar en blanco";
    public static final String MSG_TITULO_REQUIRED = "El titulo es obligatorio";
    public static final String MSG_DESCRIPCION_NOT_BLANK = "La descripcion no puede estar en blanco";
    public static final String MSG_DESCRIPCION_REQUIRED = "La descripcion es obligatorio";


    // Mensajes para los DTO
    public static final String MSG_CURSO_ID_REQUIRED = "El id del curso es obligatorio";
    public static final String MSG_CURSO_ID_POSITIVE = "El ID del curso debe ser un número positivo";
    public static final String MSG_ESTUDIANTE_ID_REQUIRED = "El id del estudiante es obligatorio";
    public static final String MSG_ESTUDIANTE_ID_POSITIVE = "El ID del estudiante debe ser un número positivo";
    public static final String MSG_EVENT_NOT_BLANK = "El evento no puede estar en blanco";
    public static final String MSG_EVENT_REQUIRED = "El evento es obligatorio";
    public static final String MSG_VALUE_NOT_BLANK = "El valor no puede estar en blanco";
    public static final String MSG_VALUE_REQUIRED = "El valor es obligatorio";

    public AppConstant() {
    }
}
