package com.ubx.timesheet.service;

import com.ubx.timesheet.model.*;
import com.ubx.timesheet.repository.*;
import com.ubx.timesheet.dto.TimesheetRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TimesheetService(
            TimesheetRepository timesheetRepository,
            TaskRepository taskRepository,
            UserService userService) {
        this.timesheetRepository = timesheetRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional
    public Timesheet submitTimesheet(Long userId, TimesheetRequest request) {
        User user = userService.getCurrentUser(userId);
        Task task = taskRepository.findById(request.getTaskId())
            .orElseThrow(() -> new RuntimeException("Task not found"));

        Timesheet timesheet = new Timesheet();
        timesheet.setUser(user);
        timesheet.setTask(task);
        timesheet.setDate(request.getDate());
        timesheet.setHoursWorked(request.getHoursWorked());
        timesheet.setOvertimeHours(request.getOvertimeHours());
        timesheet.setComments(request.getComments());
        timesheet.setStatus(TimesheetStatus.PENDING);

        return timesheetRepository.save(timesheet);
    }

    @Transactional(readOnly = true)
    public List<Timesheet> getUserTimesheets(Long userId) {
        return timesheetRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Timesheet> getPendingTimesheets() {
        return timesheetRepository.findByStatus(TimesheetStatus.PENDING);
    }

    @Transactional(readOnly = true)
    public List<Timesheet> getDepartmentTimesheets(Long departmentId) {
        return timesheetRepository.findByUserDepartmentId(departmentId);
    }
}
