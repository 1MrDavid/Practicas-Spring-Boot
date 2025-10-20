package com.springcourse.test1.dto;

import com.springcourse.test1.models.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectResponseDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TaskDTO> tasks;
    private List<EmployeeDTO> employees;

    // Constructor que recibe una entidad Project y la convierte a DTO
    public ProjectResponseDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.startDate = project.getStartdate();
        this.endDate = project.getEndDate();

        // Convertir Tasks a TaskDTOs
        this.tasks = project.getTask().stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());

        // Convertir Employees a EmployeeDTOs
        this.employees = project.getEmployees().stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }
}
