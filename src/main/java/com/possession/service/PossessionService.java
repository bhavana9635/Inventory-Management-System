package com.possession.service;

import com.possession.model.Possession;
import com.possession.model.DuplicateGroup;
import com.possession.repository.PossessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class PossessionService {
    
    @Autowired
    private PossessionRepository possessionRepository;
    
    public List<Possession> getAllPossessions() {
        return possessionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Possession> getAllPossessionsWithReminders() {
        return possessionRepository.findAllWithReminders();
    }
    
    public Optional<Possession> getPossessionById(Long id) {
        return possessionRepository.findById(id);
    }
    
    public Possession savePossession(Possession possession) {
        return possessionRepository.save(possession);
    }
    
    public void deletePossession(Long id) {
        possessionRepository.deleteById(id);
    }
    
    public List<Possession> searchPossessions(String searchTerm) {
        return possessionRepository.searchPossessions(searchTerm);
    }
    
    @Transactional(readOnly = true)
    public List<Possession> searchPossessionsWithReminders(String searchTerm) {
        return possessionRepository.searchPossessionsWithReminders(searchTerm);
    }
    
    public List<Possession> findByCategory(String category) {
        return possessionRepository.findByCategory(category);
    }
    
    @Transactional(readOnly = true)
    public List<Possession> findByCategoryWithReminders(String category) {
        return possessionRepository.findByCategoryWithReminders(category);
    }
    
    public List<Possession> findDuplicates(String serialNumber) {
        return possessionRepository.findBySerialNumber(serialNumber);
    }
    
    @Transactional(readOnly = true)
    public List<Possession> findDuplicatesWithReminders(String serialNumber) {
        return possessionRepository.findBySerialNumberWithReminders(serialNumber);
    }
    
    public List<DuplicateGroup> findDuplicateGroups() {
        Map<String, List<Possession>> groups = possessionRepository.findAll().stream()
                .filter(p -> p.getSerialNumber() != null && !p.getSerialNumber().isEmpty())
                .collect(Collectors.groupingBy(Possession::getSerialNumber))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        return groups.entrySet().stream()
                .map(entry -> new DuplicateGroup(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
} 