package back.repository.contracts;

import back.model.Card;

import java.util.List;
import java.util.UUID;

public interface CardRepository {

    boolean add(Card card);

    boolean isUniqueCard(String cardNumber);

    List<Card> getMyCards(UUID userId);

    Card findCardByID(UUID id);

}
