package com.cesarlead.DAReports.config;

public class AppConstant {

    // Mensajes de log
    public static final String LOG_ENTERING_METHOD = "Entrando al método: {}";
    public static final String LOG_EXITING_METHOD = "Saliendo del método: {}";

    // Para LoggingFilter usado para trazabilidad
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    public static final String TRACE_ID_MDC_KEY = "traceId";

    // Mensajes de controller y service de cursos
    public static final String LOG_COURSE_NOT_FOUND = "Curso no encontrado: {}";
    public static final String LOG_COURSE_CREATED = "Curso creado con ID: {}";
    public static final String LOG_FINDING_COURSE = "Buscando Curso con ID: {}";
    public static final String LOG_RETRIEVING_ALL_COURSE = "Recuperando lista de todos los Curso";
    public static final String ERROR_COURSE_NOT_FOUND = "Curso no encontrado";
    public static final String LOG_COURSE_CONSULTED = "Curso consultado con ID: {}";
    public static final String LOG_COURSE_STUDENTS_CONSULTED = "Consultado estudiantes del curso con ID: {}";

    public static final String LOG_ENROLLMENT_CREATED = "Inscripcion creada del estudiante con ID: {} para el curso con ID: {}";
    public static final String LOG_ENROLLMENT_CONSULTING = "Consultando inscripcion del estudinate: {} para el curso: {}";
    public static final String LOG_ENROLLMENT_NOT_FOUND = "Inscripcion del estudiante {} no encontrado en el curso {}";


    // Mensajes de controller y service de inscripciones
    public static final String LOG_VALIDATE_STUDENT = "Validando existencia del estudiante con ID: {}";
    public static final String LOG_STUDENT_VALIDATED = "Estudiante validado exitosamente: {} {}";
    public static final String LOG_STUDENT_NOT_FOUND = "Estudiante no encontrado: {}";
    public static final String LOG_STUDENT_UNEXPECTED_ERROR = "Error inesperado validando al estudiante: {}";

    public static final String LOG_VALIDATE_COURSE = "Validando información del curso con ID: {}";
    public static final String LOG_ENROLLMENT_CREATING = "Creando inscripción para estudiante {} en el curso {}";
    public static final String LOG_ENROLLMENT_SAVED = "Inscripción creada exitosamente con ID: {}";

    // Mensajes para los DTO
    public static final String MSG_CURSO_ID_REQUIRED = "El id del curso es obligatorio";
    public static final String MSG_CURSO_ID_POSITIVE = "El ID del curso debe ser un número positivo";
    public static final String MSG_ESTUDIANTE_ID_REQUIRED = "El id del estudiante es obligatorio";
    public static final String MSG_ESTUDIANTE_ID_POSITIVE = "El ID del estudiante debe ser un número positivo";
    public static final String MSG_TITULO_NOT_BLANK = "El titulo no puede estar en blanco";
    public static final String MSG_TITULO_REQUIRED = "El titulo es obligatorio";
    public static final String MSG_DESCRIPCION_NOT_BLANK = "La descripcion no puede estar en blanco";
    public static final String MSG_DESCRIPCION_REQUIRED = "La descripcion es obligatorio";

    public AppConstant() {
    }
}
