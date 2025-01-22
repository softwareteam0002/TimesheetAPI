package com.ubx.timesheet.repository;

import com.ubx.timesheet.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDepartmentId(Long departmentId);
}
