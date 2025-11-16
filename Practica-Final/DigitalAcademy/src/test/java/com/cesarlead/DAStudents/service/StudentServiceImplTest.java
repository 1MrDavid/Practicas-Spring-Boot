package com.cesarlead.DAStudents.service;

import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.exception.ResourceNotFoundException;
import com.cesarlead.DAStudents.mapper.MapperStudent;
import com.cesarlead.DAStudents.model.Student;
import com.cesarlead.DAStudents.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// JUnit 5 para test -> @Test, @BeforeEach, assertEquals
// Mockito para crear mocks (objetos simulados) -> @Mock, @InjectMocks, MockitoAnnotations

class StudentServiceImplTest {

    // @Mock -> Le dice a mockito que cree objetos simulados de estas clases

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MapperStudent mapper;

    // Se simula mapper y repository para hacer pruebas sin depende de la base de datos

    // ---

    // @InjectMocks → Le indica a Mockito que inyecte los mocks en la clase StudentServiceImpl
    // Mockito inyecta el studentRepository y mapper simulado en el constructor de StudentServiceImpl

    @InjectMocks
    private StudentServiceImpl studentService;

    // @BeforeEach → Metodo que se ejecuta antes de cada test, para inicializar el estado.

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this) → Inicializa los mocks declarados con @Mock y @InjectMocks.
        MockitoAnnotations.openMocks(this);
    }

    // @Test -> Marca un metodo como test unitario. Se ejecuta automáticamente.

    @Test
    void testFindById_Success() {
        // Se crea un Student simulado
        Student student = new Student();
        student.setId(1L);
        student.setNombre("Juan");
        student.setApellido("Perez");
        student.setEmail("juan@example.com");

        //Se crea un EstudianteDTO simulado
        EstudianteDTO estudianteDTO = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());

        // Simula que el repositorio devuelve el estudiante.
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        // Simula la conversion a DTO
        when(mapper.mapTopEstudianteDTO(student)).thenReturn(estudianteDTO);

        // Metodo real al servicio.
        EstudianteDTO result = studentService.findById(1L);

        // Verifica que el resultado no sea null
        assertNotNull(result);

        // Verifica que el nombre coincida.
        assertEquals("Juan", result.nombre());

        // Se asegura que el metodo findById se llamo exactamente una vez.
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        // Simula el caso donde el estudiante no existe
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Verifica que se lance la excepción ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> studentService.findById(1L));

        // Confirma que findById se llamó una vez.
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAll() {
        // Simula que hay dos estudiantes en la base de datos.
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(2L);

        EstudianteDTO dto1 = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());
        EstudianteDTO dto2 = new EstudianteDTO(2L, "Ana", "Lopez", "ana@example.com", LocalDateTime.now());

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));
        when(mapper.mapTopEstudianteDTO(student1)).thenReturn(dto1);
        when(mapper.mapTopEstudianteDTO(student2)).thenReturn(dto2);

        // Verifica que getAll() devuelva dos DTOs correctamente mapeados.
        var result = studentService.getAll();

        assertEquals(2, result.size());

        // Asegura que se llamó al repositorio una vez.
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testCreateStudent() {
        // Simula la creacion de un estudiante.
        CrearEstudianteDTO request = new CrearEstudianteDTO("Juan", "Perez", "juan@example.com");

        Student student = new Student();
        student.setNombre("Juan");
        student.setApellido("Perez");
        student.setEmail("juan@example.com");

        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setNombre("Juan");
        savedStudent.setApellido("Perez");
        savedStudent.setEmail("juan@example.com");

        EstudianteDTO estudianteDTO = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());

        // Simula que la base de datos asigna un ID al estudiante.
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);
        when(mapper.mapTopEstudianteDTO(savedStudent)).thenReturn(estudianteDTO);

        EstudianteDTO result = studentService.createStudent(request);

        // Verifica que el resultado tenga el ID correcto y que save se haya llamado exactamente una vez.
        assertNotNull(result);
        assertEquals(1L, result.id());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}
