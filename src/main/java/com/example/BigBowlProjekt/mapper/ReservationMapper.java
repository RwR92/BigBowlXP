package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.dto.CustomerDTO;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.model.Customer;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.dto.ReservationDTO;

import java.util.ArrayList;
import java.util.List;


public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        List<ActivityDTO> activitiesDTO = new ArrayList<>();

        for (Activity activity : reservation.getActivities()) {
            activitiesDTO.add(ActivityMapper.toDTO(activity));
        }

        return new ReservationDTO(
                reservation.getId(),
                reservation.getName(),
                activitiesDTO
        );
    }
    private static CustomerDTO toCustomerDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getNumber()
        );
    }
}
