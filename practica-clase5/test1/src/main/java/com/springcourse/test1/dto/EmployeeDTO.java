package com.springcourse.test1.dto;

import com.springcourse.test1.models.Employee;

public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String role;

    // Constructor que recibe una entidad Employee
    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstname();
        this.lastName = employee.getLastname();
        this.role = employee.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
