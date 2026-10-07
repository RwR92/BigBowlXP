        package com.example.BigBowlProjekt.dto;

        import com.example.BigBowlProjekt.model.Activity;

        import java.util.List;

        public record ReservationDTO(
                Long id,
                String name,
                List<ActivityDTO> activities
        ) {
        }
