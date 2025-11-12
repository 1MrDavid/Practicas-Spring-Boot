package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperCourse;
import com.cesarlead.DACourses.model.Course;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesarlead.DACourses.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final MapperCourse mapper;

  public CourseServiceImpl(
      CourseRepository courseRepository,
      MapperCourse mapperCourse) {
    this.courseRepository = courseRepository;
    this.mapper = mapperCourse;
  }

  // Busca curso por id
  @Override
  public CursoDTO findById(Long id) {

    Course course = courseRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

    return mapper.mapToCursoDTO(course);
  }

  // Busca todos los cursos
  @Override
  public List<CursoDTO> getAll() {
    return courseRepository.findAll().stream()
        .map(mapper::mapToCursoDTO)
        .toList();
  }

  // Crear nuevo curso
  @Transactional
  @Override
  public CursoDTO createCourse(CursoDTO request) {

    Course curso = new Course();
    curso.setTitulo(request.titulo());
    curso.setDescripcion(request.descripcion());

    Course savedCourse = courseRepository.save(curso);
    return mapper.mapToCursoDTO(savedCourse);
  }

}
