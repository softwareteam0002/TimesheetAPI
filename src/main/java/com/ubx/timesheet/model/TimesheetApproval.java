package com.ubx.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "timesheet_approvals")
public class TimesheetApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "timesheet_id", nullable = false)
    private Timesheet timesheet;

    @ManyToOne
    @JoinColumn(name = "approved_by", nullable = false)
    private User approvedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TimesheetStatus status;

    private String comments;

    private LocalDateTime approvedAt;
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
