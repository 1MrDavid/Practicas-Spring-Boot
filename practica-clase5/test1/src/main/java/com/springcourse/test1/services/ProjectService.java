package com.springcourse.test1.services;

import com.springcourse.test1.dto.ProjectRequestDTO;
import com.springcourse.test1.dto.ProjectResponseDTO;
import com.springcourse.test1.dto.TaskDTO;
import com.springcourse.test1.dto.TaskRequestDTO;
import com.springcourse.test1.models.Employee;
import com.springcourse.test1.models.Project;
import com.springcourse.test1.models.Task;
import com.springcourse.test1.repositories.EmployeeRepository;
import com.springcourse.test1.repositories.ProjectRepository;
import com.springcourse.test1.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Crear nuevo proyecto
    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequest) {
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setStartdate(projectRequest.getStartDate());
        project.setEndDate(projectRequest.getEndDate());

        Project savedProject = projectRepository.save(project);
        return new ProjectResponseDTO(savedProject);
    }

    // Obtener todas las tareas de un proyecto
    @Transactional
    public List<TaskDTO> getProjectTasks(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        return project.getTask().stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    // Agregar tarea a un proyecto existente
    @Transactional
    public TaskDTO addTaskToProject(Long projectId, TaskRequestDTO taskRequest) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        Task task = new Task();
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setDueDate(taskRequest.getDueDate());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);
        return new TaskDTO(savedTask);
    }

    // Asignar empleado existente a un proyecto
    @Transactional
    public ProjectResponseDTO assignEmployeeToProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        project.addEmployee(employee);
        Project updatedProject = projectRepository.save(project);

        return new ProjectResponseDTO(updatedProject);
    }

    // Obtener proyecto por ID
    @Transactional
    public ProjectResponseDTO getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        return new ProjectResponseDTO(project);
    }
}
