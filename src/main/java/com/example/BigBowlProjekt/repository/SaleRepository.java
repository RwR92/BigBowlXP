package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> getAllBySaleDateIsBetween(LocalDate startDate, LocalDate endDate);
}
