package plus.delta.schoolcalender.model.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
public record LoginRequest(String username, String password) {}
