package back.payload;

import java.util.UUID;

public record  CardAddDTO(String cardNumber, int cardPassword, Double balance, UUID userId, boolean isActive){
}
