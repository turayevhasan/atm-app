package back.repository.contracts;

import back.model.Bankomat;
import back.model.Card;

import java.util.List;

public interface BankomatRepository {
    List<Bankomat> all();

    boolean fill(Card card, Double money, float percentTransaction);
}
