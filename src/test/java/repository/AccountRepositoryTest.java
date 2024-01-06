package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
import com.wallet.app.repository.AccountRepository;


public class AccountRepositoryTest {
    AccountRepository repo = new AccountRepository();

    @Test
    void save() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 6, 10, 0, 0);
        Account account = new Account("f028d677-18fb-4e00-af96-e482e54236a1", "Account 1", 0.0, Timestamp.valueOf(dateTime), "Bank", new Currency("1", "Ariary", "MGA"), null);
        // repo.save(account);
        // assertEquals(account.getName(), repo.getById("f028d677-18fb-4e00-af96-e482e54236a1").getName());
    }
    
    @Test
    void getById() {
        Account account = new Account();
        assertEquals(account.getClass(), repo.getById("f028d677-18fb-4e00-af96-e482e54236a1").getClass());
    }
}
