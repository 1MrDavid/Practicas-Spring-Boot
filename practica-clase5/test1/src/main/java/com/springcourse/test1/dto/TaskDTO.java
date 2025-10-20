package com.springcourse.test1.dto;

import com.springcourse.test1.models.Task;

import java.time.LocalDate;

public class TaskDTO {
    private Long id;
    private String description;
    private String status;
    private LocalDate dueDate;
    private String assignedEmployee; // Solo el nombre del empleado, no toda la entidad

    // Constructor que recibe una entidad Task
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.dueDate = task.getDueDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(String assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }
}
