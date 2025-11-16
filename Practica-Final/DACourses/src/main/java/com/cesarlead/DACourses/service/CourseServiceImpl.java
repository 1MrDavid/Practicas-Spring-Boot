package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.config.AppConstant;
import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperCourse;
import com.cesarlead.DACourses.model.Course;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cesarlead.DACourses.repository.CourseRepository;

import jakarta.transaction.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final MapperCourse mapper;

  // Busca curso por id
  @Override
  public CursoDTO findById(Long id) {
    log.debug(AppConstant.LOG_ENTERING_METHOD, "findById");
    log.info(AppConstant.LOG_FINDING_COURSE, id);

    Course course = courseRepository.findById(id)
        .orElseThrow(() -> {
            log.warn(AppConstant.LOG_COURSE_NOT_FOUND, id);
            return new ResourceNotFoundException(AppConstant.ERROR_COURSE_NOT_FOUND);
        });

    log.info(AppConstant.LOG_COURSE_CONSULTED, id);
    log.debug(AppConstant.LOG_EXITING_METHOD, "findById");

    return mapper.mapToCursoDTO(course);
  }

  // Busca todos los cursos
  @Override
  public List<CursoDTO> getAll() {
    log.debug(AppConstant.LOG_ENTERING_METHOD, "getAll");
    log.info(AppConstant.LOG_RETRIEVING_ALL_COURSE);

    List<CursoDTO> cursos = courseRepository.findAll().stream()
        .map(mapper::mapToCursoDTO)
        .toList();

    log.debug(AppConstant.LOG_EXITING_METHOD, "getAll");

    return cursos;
  }

  // Crear nuevo curso
  @Transactional
  @Override
  public CursoDTO createCourse(CursoDTO request) {

    log.debug(AppConstant.LOG_ENTERING_METHOD, "createCourse");

    Course curso = new Course();
    curso.setTitulo(request.titulo());
    curso.setDescripcion(request.descripcion());

    Course savedCourse = courseRepository.save(curso);

    log.info(AppConstant.LOG_COURSE_CREATED, savedCourse.getId());
    log.debug(AppConstant.LOG_EXITING_METHOD, "createCourse");

    return mapper.mapToCursoDTO(savedCourse);
  }

}
