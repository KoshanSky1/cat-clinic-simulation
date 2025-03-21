package cat_clinic_simulation.dto.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record NewMasterDto(
        @Positive(message = "Number must be positive")
        Integer number,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "Date cannot be null")
        LocalDate date,
        @NotBlank(message = "Description can not be blank")
        String description
) {
}