package com.cesarlead.DAStudents.config;

public class AppConstant {

    // Evitar magic string

    // Mensajes de log
    public static final String LOG_ENTERING_METHOD = "Entrando al método: {}";
    public static final String LOG_EXITING_METHOD = "Saliendo del método: {}";

    // Mensajes de controller de estudiantes
    public static final String LOG_STUDENT_NOT_FOUND = "Estudiante no encontrado: {}";
    public static final String LOG_STUDENT_CREATED = "Estudiante creado con ID: {}";
    public static final String LOG_FINDING_STUDENT = "Buscando estudiante con ID: {}";
    public static final String LOG_RETRIEVING_ALL_STUDENTS = "Recuperando lista de todos los estudiantes";
    public static final String ERROR_STUDENT_NOT_FOUND = "Estudiante no encontrado";
    public static final String LOG_USER_CONSULTED = "Estudiante consultado con ID: {}";

    // Mensajes de validación para Estudiante
    public static final String NOMBRE_NOT_BLANK = "El nombre no puede estar en blanco";
    public static final String NOMBRE_REQUIRED = "El nombre es obligatorio";

    public static final String APELLIDO_NOT_BLANK = "El apellido no puede estar en blanco";
    public static final String APELLIDO_REQUIRED = "El apellido es obligatorio";

    public static final String EMAIL_INVALID = "Debe ser una dirección de email válida";
    public static final String EMAIL_NOT_BLANK = "El email no puede estar en blanco";

    // Para LoggingFilter usado para trazabilidad
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";

    public AppConstant() {
    }
}
