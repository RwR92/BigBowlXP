package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
