package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Customer;

import java.util.Locale;

public record CustomerDTO(

       Long id,
       String firstName,
       String lastName,
       String email,
       String number
) {
}
