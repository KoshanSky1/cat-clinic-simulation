package cat_clinic_simulation.dto.detail;

import lombok.Builder;

@Builder
public record DetailDto(
        Long id,
        Long masterId,
        String name,
        Double amount
) {
}