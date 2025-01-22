package com.ubx.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "timesheets")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal hoursWorked;

    private BigDecimal overtimeHours;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TimesheetStatus status;

    private String comments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
