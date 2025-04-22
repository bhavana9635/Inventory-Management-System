package com.possession.repository;

import com.possession.model.Possession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PossessionRepository extends JpaRepository<Possession, Long> {
    List<Possession> findByNameContainingIgnoreCase(String name);
    List<Possession> findByCategory(String category);
    
    @Query("SELECT p FROM Possession p WHERE p.serialNumber = ?1")
    List<Possession> findBySerialNumber(String serialNumber);
    
    @Query("SELECT p FROM Possession p LEFT JOIN FETCH p.reminders WHERE p.category = :category")
    List<Possession> findByCategoryWithReminders(@Param("category") String category);
    
    @Query("SELECT p FROM Possession p LEFT JOIN FETCH p.reminders WHERE p.serialNumber = :serialNumber")
    List<Possession> findBySerialNumberWithReminders(@Param("serialNumber") String serialNumber);
    
    @Query("SELECT DISTINCT p FROM Possession p LEFT JOIN FETCH p.reminders")
    List<Possession> findAllWithReminders();
    
    @Query("SELECT DISTINCT p FROM Possession p LEFT JOIN FETCH p.reminders WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Possession> searchPossessionsWithReminders(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT p FROM Possession p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.category) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Possession> searchPossessions(@Param("searchTerm") String searchTerm);
} 