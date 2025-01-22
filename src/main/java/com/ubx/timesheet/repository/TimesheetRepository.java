package com.ubx.timesheet.repository;

import com.ubx.timesheet.model.Timesheet;
import com.ubx.timesheet.model.TimesheetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByUserId(Long userId);
    List<Timesheet> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<Timesheet> findByStatus(TimesheetStatus status);
    List<Timesheet> findByUserDepartmentId(Long departmentId);
}
