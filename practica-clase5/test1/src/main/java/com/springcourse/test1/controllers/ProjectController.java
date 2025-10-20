package com.springcourse.test1.controllers;

import com.springcourse.test1.dto.ProjectRequestDTO;
import com.springcourse.test1.dto.ProjectResponseDTO;
import com.springcourse.test1.dto.TaskDTO;
import com.springcourse.test1.dto.TaskRequestDTO;
import com.springcourse.test1.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // POST /projects: Crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@Valid @RequestBody ProjectRequestDTO projectRequest) {
        ProjectResponseDTO createdProject = projectService.createProject(projectRequest);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // GET /projects/{projectId}/tasks: Obtener todas las tareas de un proyecto
    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getProjectTasks(@PathVariable Long projectId) {
        List<TaskDTO> tasks = projectService.getProjectTasks(projectId);
        return ResponseEntity.ok(tasks);
    }

    // POST /projects/{projectId}/tasks: Añadir una nueva tarea a un proyecto existente
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskDTO> addTaskToProject(
            @PathVariable Long projectId,
            @Valid @RequestBody TaskRequestDTO taskRequest) {
        TaskDTO createdTask = projectService.addTaskToProject(projectId, taskRequest);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // POST /projects/{projectId}/employees/{employeeId}: Asignar un empleado existente a un proyecto
    @PostMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<ProjectResponseDTO> assignEmployeeToProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {
        ProjectResponseDTO updatedProject = projectService.assignEmployeeToProject(projectId, employeeId);
        return ResponseEntity.ok(updatedProject);
    }

    // GET /projects/{projectId}: Obtener un proyecto específico
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable Long projectId) {
        ProjectResponseDTO project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }
}
