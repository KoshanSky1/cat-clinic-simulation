package cat_clinic_simulation.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String error,
        Integer status
) {
}