package com.cesarlead.DACourses.repository;

import com.cesarlead.DACourses.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
    //List<Long> findEstudianteIdByCursoId(Long cursoId);

    @Query("SELECT e.estudianteId FROM Enrollment e WHERE e.cursoId = :cursoId")
    List<Long> findEstudianteIdByCursoId(@Param("cursoId") Long cursoId);
}
