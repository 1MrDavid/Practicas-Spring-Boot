package com.cesarlead.DAProgress.repository;

import com.cesarlead.DAProgress.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long>, JpaSpecificationExecutor<Progress> {
    List<Progress> findByCursoId(Long cursoId);
}
