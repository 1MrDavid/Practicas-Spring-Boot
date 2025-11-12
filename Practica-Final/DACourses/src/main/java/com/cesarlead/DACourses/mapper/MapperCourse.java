package com.cesarlead.DACourses.mapper;

import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.model.Course;
import org.springframework.stereotype.Component;

@Component
public class MapperCourse {
  public CursoDTO mapToCursoDTO(Course curso) {
    return new CursoDTO(
        curso.getId(),
        curso.getTitulo(),
        curso.getDescripcion()
    );
  }
}
