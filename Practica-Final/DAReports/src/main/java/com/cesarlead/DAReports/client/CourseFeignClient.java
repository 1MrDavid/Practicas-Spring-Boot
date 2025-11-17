package com.cesarlead.DAReports.client;

import com.cesarlead.DAReports.dto.CursoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "course-service",
        url = "${course-service.url}"
)
public interface CourseFeignClient {

    @GetMapping("/{cursoId}/estudiantes")
    public ResponseEntity<List<Long>> getStudentsFromCourse(@PathVariable Long cursoId);

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCourseByID(@PathVariable Long id);
}
