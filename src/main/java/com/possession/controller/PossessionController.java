package com.possession.controller;

import com.possession.model.Possession;
import com.possession.service.PossessionService;
import com.possession.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/possessions")
public class PossessionController {
    
    @Autowired
    private PossessionService possessionService;
    
    @Autowired
    private ReminderService reminderService;
    
    @GetMapping
    public String listPossessions(Model model) {
        model.addAttribute("title", "Possessions List");
        model.addAttribute("possessions", possessionService.getAllPossessionsWithReminders());
        return "possessions/list";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("title", "Add New Possession");
        model.addAttribute("possession", new Possession());
        return "possessions/form";
    }
    
    @PostMapping
    public String createPossession(@ModelAttribute Possession possession, 
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate purchaseDate) {
        if (purchaseDate != null) {
            possession.setPurchaseDate(purchaseDate);
        }
        possessionService.savePossession(possession);
        return "redirect:/possessions";
    }
    
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Edit Possession");
        Possession possession = possessionService.getPossessionById(id).orElseThrow();
        model.addAttribute("possession", possession);
        model.addAttribute("reminders", reminderService.getRemindersByPossession(id));
        return "possessions/form";
    }
    
    @PostMapping("/{id}")
    public String updatePossession(@PathVariable Long id, 
                                 @ModelAttribute Possession possession,
                                 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate purchaseDate) {
        possession.setId(id);
        if (purchaseDate != null) {
            possession.setPurchaseDate(purchaseDate);
        }
        possessionService.savePossession(possession);
        return "redirect:/possessions";
    }
    
    @GetMapping("/{id}/delete")
    public String deletePossession(@PathVariable Long id) {
        possessionService.deletePossession(id);
        return "redirect:/possessions";
    }
    
    @GetMapping("/search")
    public String searchPossessions(@RequestParam String searchTerm, Model model) {
        model.addAttribute("title", "Search Results");
        model.addAttribute("possessions", possessionService.searchPossessionsWithReminders(searchTerm));
        return "possessions/list";
    }
    
    @GetMapping("/duplicates")
    public String showDuplicates(Model model) {
        model.addAttribute("duplicateGroups", possessionService.findDuplicateGroups());
        return "possessions/duplicates";
    }
    
    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("title", "Possession Details");
        Possession possession = possessionService.getPossessionById(id).orElseThrow();
        model.addAttribute("possession", possession);
        model.addAttribute("reminders", reminderService.getRemindersByPossession(id));
        return "possessions/details";
    }
    
    @GetMapping("/category/{category}")
    public String findByCategory(@PathVariable String category, Model model) {
        model.addAttribute("title", category + " Possessions");
        model.addAttribute("possessions", possessionService.findByCategoryWithReminders(category));
        model.addAttribute("category", category);
        return "possessions/list";
    }
} 