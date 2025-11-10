package com.cesarlead.DAStudents.mapper;

import org.springframework.stereotype.Component;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.model.Student;

@Component
public class MapperStudent {
  public EstudianteDTO mapTopEstudianteDTO(Student estudiante) {
    return new EstudianteDTO(
        estudiante.getId(),
            estudiante.getNombre(),
            estudiante.getApellido(),
            estudiante.getEmail(),
            estudiante.getFechaCreacion()
    );
  }
}
