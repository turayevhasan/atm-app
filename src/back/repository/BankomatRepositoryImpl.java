package back.repository;

import back.model.Bankomat;
import back.model.Card;
import back.repository.contracts.BankomatRepository;

import java.util.List;

public class BankomatRepositoryImpl implements BankomatRepository {

    private static final BankomatRepository instance = new BankomatRepositoryImpl();

    private BankomatRepositoryImpl() {
    }

    public static BankomatRepository getInstance() {
        return instance;
    }

    List<Bankomat> bankomats = List.of(
            new Bankomat("KapitalBank", 1.0F),
            new Bankomat("UzumBank", 1.5F),
            new Bankomat("AloqaBank", 2.0F)
    );

    @Override
    public List<Bankomat> all() {
        return bankomats;
    }

    @Override
    public boolean fill(Card card, Double money, float percentTransaction) {
        Double newBalance = card.getBalance() + money - percentTransaction * money / 100;
        card.setBalance(newBalance);
        return true;
    }
}
