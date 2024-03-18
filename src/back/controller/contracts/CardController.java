package back.controller.contracts;

import back.payload.CardAddDTO;
import back.payload.CardDTO;

import java.util.List;
import java.util.UUID;

public interface CardController {
    CardDTO createCard(CardAddDTO dto);

    List<CardDTO> myCards(UUID userId);

}
