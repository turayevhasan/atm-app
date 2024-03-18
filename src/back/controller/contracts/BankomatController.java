package back.controller.contracts;

import back.payload.BankomatDTO;
import back.payload.CardDTO;

import java.util.List;
import java.util.UUID;

public interface BankomatController {
    List<BankomatDTO> getAll();

    boolean fillCardBalance(UUID cardId, Double money, float percentTransaction);
}
