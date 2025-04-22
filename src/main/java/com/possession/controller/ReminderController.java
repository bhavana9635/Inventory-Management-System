package com.possession.controller;

import com.possession.model.Possession;
import com.possession.model.Reminder;
import com.possession.service.ReminderService;
import com.possession.service.PossessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reminders")
public class ReminderController {
    
    @Autowired
    private ReminderService reminderService;
    
    @Autowired
    private PossessionService possessionService;
    
    @GetMapping
    public String listReminders(Model model) {
        model.addAttribute("title", "Reminders List");
        model.addAttribute("reminders", reminderService.getAllReminders());
        return "reminders/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model, @RequestParam(required = false) Long possessionId) {
        model.addAttribute("title", "Add New Reminder");
        model.addAttribute("reminder", new Reminder());
        model.addAttribute("possessions", possessionService.getAllPossessions());
        
        if (possessionId != null) {
            model.addAttribute("selectedPossessionId", possessionId);
        }
        
        return "reminders/form";
    }
    
    @PostMapping
    public String createReminder(@ModelAttribute Reminder reminder, 
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDate) {
        reminder.setReminderDate(reminderDate);
        reminderService.saveReminder(reminder);
        return "redirect:/reminders";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Edit Reminder");
        Reminder reminder = reminderService.getReminderById(id).orElseThrow();
        model.addAttribute("reminder", reminder);
        model.addAttribute("possessions", possessionService.getAllPossessions());
        return "reminders/form";
    }
    
    @PostMapping("/{id}")
    public String updateReminder(@PathVariable Long id, 
                               @ModelAttribute Reminder reminder,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDate) {
        reminder.setId(id);
        reminder.setReminderDate(reminderDate);
        reminderService.saveReminder(reminder);
        return "redirect:/reminders";
    }
    
    @GetMapping("/{id}/delete")
    public String deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return "redirect:/reminders";
    }
    
    @GetMapping("/upcoming")
    public String showUpcomingReminders(Model model) {
        model.addAttribute("title", "Upcoming Reminders");
        model.addAttribute("reminders", reminderService.getUpcomingReminders());
        return "reminders/list";
    }
    
    @GetMapping("/pending")
    public String showPendingReminders(Model model) {
        model.addAttribute("title", "Pending Reminders");
        model.addAttribute("reminders", reminderService.getPendingReminders());
        return "reminders/list";
    }
    
    @GetMapping("/completed")
    public String showCompletedReminders(Model model) {
        model.addAttribute("title", "Completed Reminders");
        model.addAttribute("reminders", reminderService.getCompletedReminders());
        return "reminders/list";
    }
    
    @GetMapping("/possession/{possessionId}")
    public String showRemindersByPossession(@PathVariable Long possessionId, Model model) {
        Possession possession = possessionService.getPossessionById(possessionId).orElseThrow();
        model.addAttribute("title", "Reminders for " + possession.getName());
        model.addAttribute("reminders", reminderService.getRemindersByPossession(possessionId));
        model.addAttribute("possession", possession);
        return "reminders/list";
    }
    
    @GetMapping("/{id}/toggle")
    public String toggleReminderStatus(@PathVariable Long id) {
        reminderService.toggleReminderStatus(id);
        return "redirect:/reminders";
    }
    
    @GetMapping("/search")
    public String searchReminders(@RequestParam String searchTerm, Model model) {
        model.addAttribute("title", "Search Results");
        model.addAttribute("reminders", reminderService.searchReminders(searchTerm));
        return "reminders/list";
    }
} 