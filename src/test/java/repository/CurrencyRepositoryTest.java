package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Currency;
import com.wallet.app.repository.CurrencyRepository;

public class CurrencyRepositoryTest {
    CurrencyRepository repo = new CurrencyRepository();

    @Test
    void getById() {
        // Currency currency = new Currency("1", "Ariary", "MGA");
        // assertEquals(currency, repo.getById("1"));
    }
}
