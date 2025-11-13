package com.cesarlead.DACourses.client;

import com.cesarlead.DACourses.dto.EstudianteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "student-service",
        url = "${student-service.url}",
        path = "/api/v1/student"
)
public interface StudentFeignClient {

  @GetMapping("/{id}")
  EstudianteDTO getStudentByID(@PathVariable("id") Long id);
}
