package com.cesarlead.DAStudents.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
// @Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@Data // Trae implicito getter y setter
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private String apellido;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(name = "fecha_creacion", nullable = false)
  LocalDateTime fechaCreacion;
}
