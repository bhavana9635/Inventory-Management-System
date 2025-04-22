package com.possession.repository;

import com.possession.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByCompletedFalse();
    List<Reminder> findByCompletedTrue();
    List<Reminder> findByReminderDateBetween(LocalDateTime start, LocalDateTime end);
    List<Reminder> findByPossessionId(Long possessionId);
} 