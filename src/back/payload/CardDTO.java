package back.payload;

import java.util.UUID;

public record CardDTO(UUID id, String cardNumber, int cardPassword, Double balance, UUID userId, boolean isActive) {
}
