package com.ubx.timesheet.service;

import com.ubx.timesheet.model.Task;
import com.ubx.timesheet.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> getDepartmentTasks(Long departmentId) {
        return taskRepository.findByDepartmentId(departmentId);
    }

    @Transactional(readOnly = true)
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
