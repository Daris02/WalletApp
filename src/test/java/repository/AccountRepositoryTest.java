package repository;

import com.wallet.app.model.Account;
import com.wallet.app.repository.AccountRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {
    public AccountRepository accountRepository = new AccountRepository();
    
    @Test
    void get_account_by_id() {
        Account accountTest = new Account();
        assertEquals(accountTest.getClass(), accountRepository.getById("f37c2443-7225-436d-ba3f-1abc80a2fa9d").getClass());
    }
}
