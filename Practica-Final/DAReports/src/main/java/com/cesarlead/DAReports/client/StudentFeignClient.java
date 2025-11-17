package com.cesarlead.DAReports.client;

import com.cesarlead.DAReports.dto.EstudianteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "student-service",
        url = "${student-service.url}"
)
public interface StudentFeignClient {

  @GetMapping("/{id}")
  EstudianteDTO getStudentByID(@PathVariable("id") Long id);
}
