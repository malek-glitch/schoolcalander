package plus.delta.schoolcalender.model.DTO;

import lombok.Builder;

@Builder
public record LoginResponse(String token, java.util.Date expireIn) {
}
