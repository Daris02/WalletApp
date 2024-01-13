package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Account;
import com.wallet.app.repository.AccountRepository;


public class AccountRepositoryTest {
    AccountRepository repo = new AccountRepository();

    @Test
    void save() {
        // Account account = new Account("f028d677-18fb-4e00-af96-e482e54236a1", "Account 1", 0);
        // repo.save(account);
        // assertEquals(account.getName(), repo.getById("f028d677-18fb-4e00-af96-e482e54236a1").getName());
    }
    
    @Test
    void getById() {
        Account account = new Account();
        assertEquals(account.getClass(), repo.getById("f028d677-18fb-4e00-af96-e482e54236a1").getClass());
    }
}
