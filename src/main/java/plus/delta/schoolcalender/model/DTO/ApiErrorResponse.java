package plus.delta.schoolcalender.model.DTO;

import lombok.Builder;

@Builder
public record ApiErrorResponse(int code, String message) {

}
