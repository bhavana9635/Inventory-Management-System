package com.possession.service;

import com.possession.model.Reminder;
import com.possession.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {
    
    @Autowired
    private ReminderRepository reminderRepository;
    
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }
    
    public Optional<Reminder> getReminderById(Long id) {
        return reminderRepository.findById(id);
    }
    
    public Reminder saveReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }
    
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
    
    public List<Reminder> getUpcomingReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekFromNow = now.plusWeeks(1);
        return reminderRepository.findByReminderDateBetween(now, weekFromNow);
    }
    
    public List<Reminder> getRemindersByPossession(Long possessionId) {
        return reminderRepository.findByPossessionId(possessionId);
    }
    
    public List<Reminder> getPendingReminders() {
        return reminderRepository.findByCompletedFalse();
    }
    
    public List<Reminder> getCompletedReminders() {
        return reminderRepository.findByCompletedTrue();
    }
    
    public Reminder toggleReminderStatus(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found with id: " + id));
        
        reminder.setCompleted(!reminder.isCompleted());
        return reminderRepository.save(reminder);
    }
    
    public List<Reminder> searchReminders(String searchTerm) {
        return reminderRepository.findAll().stream()
                .filter(reminder -> 
                    reminder.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    (reminder.getDescription() != null && reminder.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) ||
                    reminder.getPossession().getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }
} 