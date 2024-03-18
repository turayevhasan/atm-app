package back.controller;

import back.controller.contracts.CardController;
import back.model.Card;
import back.model.User;
import back.payload.CardAddDTO;
import back.payload.CardDTO;
import back.repository.CardRepositoryImpl;
import back.repository.UserRepositoryImpl;
import back.repository.contracts.CardRepository;
import back.repository.contracts.UserRepository;
import back.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardControllerImpl implements CardController {
    private static final CardController instance = new CardControllerImpl();

    private CardControllerImpl() {

    }

    public static CardController getInstance() {
        return instance;
    }

    private final UserRepository userRepository = UserRepositoryImpl.getInstance();
    private final CardRepository cardRepository = CardRepositoryImpl.getInstance();

    @Override
    public CardDTO createCard(CardAddDTO dto) {
        boolean isValidCardNumber = Utils.isValidCardNumber(dto.cardNumber());
        if (!isValidCardNumber) {
            throw new RuntimeException("Card numbers must includes only 16 digits ❌");
        }

        boolean isValidCardPassword = Utils.isValidCardPassword(dto.cardPassword());
        if (!isValidCardPassword) {
            throw new RuntimeException("Card password length must includes 4 digits ❌");
        }

        boolean isUniqueCard = cardRepository.isUniqueCard(dto.cardNumber());
        if (!isUniqueCard) {
            throw new RuntimeException("This card number is not unique ❌");
        }

        User user = userRepository.findByUserId(dto.userId());
        if (user == null) {
            throw new RuntimeException("You cannot add this card this user. Because this user is not registered ❌");
        }

        Card card = new Card(dto.cardNumber(), dto.cardPassword(), dto.balance(), user.getId(), true);
        cardRepository.add(card);

        return new CardDTO(card.getId(), card.getCardNumber(), card.getCardPassword(), card.getBalance(), card.getUserId(), card.isActive());
    }

    @Override
    public List<CardDTO> myCards(UUID userId) {
        List<CardDTO> all = new ArrayList<>();

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("This user is not registered ❌");
        }

        List<Card> myCards = cardRepository.getMyCards(userId);
        if (myCards.isEmpty()) {
            throw new RuntimeException("You have no cards ❌");
        }

        for (Card myCard : myCards) {
            all.add(new CardDTO(myCard.getId(), myCard.getCardNumber(), myCard.getCardPassword(), myCard.getBalance(), myCard.getUserId(), myCard.isActive()));
        }
        return all;
    }
}
