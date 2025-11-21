package com.cesarlead.DAProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@NoArgsConstructor
@AllArgsConstructor
// Getter, Setter, HashMap, toString, Constructors(AllArgs, NoArgs, RequiredArgs)
@Data
public class Progress {

    // TODO: Agregar OneToMany, ManyToMany, cascade, etc..

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long cursoId;

    @Column(nullable = false)
    Long estudianteId;

    @Column(nullable = false)
    String tipoEvento;

    @Column(nullable = true)
    String valor;

    @Column(nullable = false)
    LocalDateTime timestamp;

}
