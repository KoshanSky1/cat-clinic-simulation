package cat_clinic_simulation.dto.detail;

import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UpdateDetailDto(
        String name,
        @Positive(message = "Amount must be positive")
        Double amount
) {
}