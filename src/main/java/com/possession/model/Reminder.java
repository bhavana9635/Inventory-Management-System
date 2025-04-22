package com.possession.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "possession_id", nullable = false)
    private Possession possession;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "reminder_date", nullable = false)
    private LocalDateTime reminderDate;

    private boolean completed;
    
    @Transient
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    public String getStatus() {
        if (completed) {
            return "COMPLETED";
        }
        
        LocalDateTime now = LocalDateTime.now();
        
        if (reminderDate.isBefore(now)) {
            return "OVERDUE";
        } else if (reminderDate.toLocalDate().equals(now.toLocalDate())) {
            return "TODAY";
        } else {
            return "UPCOMING";
        }
    }
} 