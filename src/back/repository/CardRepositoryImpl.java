package back.repository;

import back.model.Card;
import back.repository.contracts.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepositoryImpl implements CardRepository {

    private static final CardRepository instance = new CardRepositoryImpl();

    private CardRepositoryImpl() {
    }

    public static CardRepository getInstance() {
        return instance;
    }

    private final List<Card> cards = new ArrayList<>();

    @Override
    public boolean add(Card card) {
        return cards.add(card);
    }

    @Override
    public boolean isUniqueCard(String cardNumber) {
        for (Card card : cards) {
            if (card != null && card.getCardNumber().equals(cardNumber))
                return false;
        }
        return true;
    }

    @Override
    public List<Card> getMyCards(UUID userId) {
        List<Card> all = new ArrayList<>();

        for (Card card : cards) {
            if (card != null && card.getUserId().equals(userId))
                all.add(card);
        }
        return all;
    }

    @Override
    public Card findCardByID(UUID id) {
        for (Card card : cards) {
            if (card != null && card.getId().equals(id))
                return card;
        }
        return null;
    }


}
