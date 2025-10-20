package com.springcourse.test1.repositories;

import com.springcourse.test1.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    List<Task> findByProjectIdAndStatus(Long projectId, String status);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStatus(String status);
}
