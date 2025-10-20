package com.springcourse.test1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskRequestDTO {
    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Status is mandatory")
    private String status; // "PENDING", "IN_PROGRESS", "COMPLETED"

    @NotNull(message = "Due date is mandatory")
    private LocalDate dueDate;

    private Long employeeId; // Opcional - para asignar a un empleado

    public TaskRequestDTO() {}

    public TaskRequestDTO(String description, String status, LocalDate dueDate, Long employeeId) {
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.employeeId = employeeId;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
