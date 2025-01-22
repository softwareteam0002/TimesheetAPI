package com.ubx.timesheet.repository;

import com.ubx.timesheet.model.TimesheetApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimesheetApprovalRepository extends JpaRepository<TimesheetApproval, Long> {
    List<TimesheetApproval> findByTimesheetId(Long timesheetId);
    List<TimesheetApproval> findByApprovedById(Long approvedById);
}
