package com.ubx.timesheet.controller;

import com.ubx.timesheet.dto.TimesheetRequest;
import com.ubx.timesheet.model.Timesheet;
import com.ubx.timesheet.security.UserPrincipal;
import com.ubx.timesheet.service.TimesheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<Timesheet> submitTimesheet(
            @AuthenticationPrincipal UserPrincipal currentUser,
            @RequestBody TimesheetRequest request) {
        return ResponseEntity.ok(timesheetService.submitTimesheet(currentUser.getId(), request));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<List<Timesheet>> getMyTimesheets(
            @AuthenticationPrincipal UserPrincipal currentUser) {
        return ResponseEntity.ok(timesheetService.getUserTimesheets(currentUser.getId()));
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<List<Timesheet>> getPendingTimesheets() {
        return ResponseEntity.ok(timesheetService.getPendingTimesheets());
    }

    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasAnyRole('SUPERVISOR', 'DIRECTOR')")
    public ResponseEntity<List<Timesheet>> getDepartmentTimesheets(
            @PathVariable Long departmentId) {
        return ResponseEntity.ok(timesheetService.getDepartmentTimesheets(departmentId));
    }
}
