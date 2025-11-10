package com.cesarlead.DAStudents.service;

import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;

import java.util.List;

public interface StudentService {

  EstudianteDTO findById(Long id);

  List<EstudianteDTO> getAll();

  EstudianteDTO createStudent(CrearEstudianteDTO request);
}
