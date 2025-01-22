package com.ubx.timesheet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
public class TimesheetRequest {
    @NotNull
    private Long taskId;
    
    @NotNull
    private LocalDate date;
    
    @NotNull
    @Positive
    private BigDecimal hoursWorked;
    
    private BigDecimal overtimeHours;
    private String comments;
}
