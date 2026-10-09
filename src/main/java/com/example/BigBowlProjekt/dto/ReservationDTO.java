        package com.example.BigBowlProjekt.dto;

        import java.util.List;

        public record ReservationDTO(
                Long id,
                String name,
                List<ActivityDTO> activities
        ) {
        }
