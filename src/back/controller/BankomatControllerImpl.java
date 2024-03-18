package back.controller;

import back.controller.contracts.BankomatController;
import back.model.Bankomat;
import back.model.Card;
import back.payload.BankomatDTO;
import back.payload.CardDTO;
import back.repository.BankomatRepositoryImpl;
import back.repository.CardRepositoryImpl;
import back.repository.contracts.BankomatRepository;
import back.repository.contracts.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankomatControllerImpl implements BankomatController {
    private static final BankomatController instance = new BankomatControllerImpl();

    private BankomatControllerImpl() {
    }

    public static BankomatController getInstance() {
        return instance;
    }

    private final BankomatRepository bankomatRepository = BankomatRepositoryImpl.getInstance();
    private final CardRepository cardRepository = CardRepositoryImpl.getInstance();

    @Override
    public List<BankomatDTO> getAll() {
        List<BankomatDTO> bankomats = new ArrayList<>();

        List<Bankomat> all = bankomatRepository.all();

        for (Bankomat one : all) {
            bankomats.add(new BankomatDTO(one.getName(), one.getPercentTransaction()));
        }
        return bankomats;
    }

    @Override
    public boolean fillCardBalance(UUID cardId, Double money, float percentTransaction) {
        Card card = cardRepository.findCardByID(cardId);

        if (card == null) {
            throw new RuntimeException("Card is not found ❌");
        }

        bankomatRepository.fill(card, money, percentTransaction);
        return true;
    }


}
