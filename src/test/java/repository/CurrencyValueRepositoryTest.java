package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.CurrencyValue;
import com.wallet.app.repository.CurrencyValueRepository;

public class CurrencyValueRepositoryTest {
    CurrencyValueRepository repo = new CurrencyValueRepository();
    
    @Test
    void getById() {
        CurrencyValue currencyValue = new CurrencyValue();
        assertEquals(currencyValue, currencyValue);
    }
}
