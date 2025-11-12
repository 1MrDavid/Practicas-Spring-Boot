package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.dto.CursoDTO;

import java.util.List;

public interface CourseService {

    public CursoDTO findById(Long id);

    public List<CursoDTO> getAll();

    public CursoDTO createCourse(CursoDTO request);
}
