package cat_clinic_simulation.dto.detail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record NewDetailDto(
        @NotBlank(message = "Name can not be blank")
        String name,
        @Positive(message = "Amount must be positive")
        Double amount
) {
}