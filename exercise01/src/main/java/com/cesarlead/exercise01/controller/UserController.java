package com.cesarlead.exercise01.controller;

import com.cesarlead.exercise01.dto.UserRegistrationRequest;
import com.cesarlead.exercise01.groups.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserRegistrationRequest request) {
        System.out.println("User registration request is valid: " + request.username());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable Long id,
            @Validated(OnUpdate.class)
            @RequestBody UserRegistrationRequest request
    ) {
        return ResponseEntity.ok("El cliente con ID: " + id + " ha sido modificado");
    }

}
