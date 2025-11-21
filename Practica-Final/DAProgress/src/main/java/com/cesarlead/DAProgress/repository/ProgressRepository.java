package com.cesarlead.DAProgress.repository;

import com.cesarlead.DAProgress.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

                                            // CRUD Sencillo                Consultas complejas
public interface ProgressRepository extends JpaRepository<Progress, Long>, JpaSpecificationExecutor<Progress> {
    List<Progress> findByCursoId(Long cursoId);

    // En la clase 5 creo que habia una tabla en el md
}
