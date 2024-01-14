package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.wallet.app.model.Account;
import com.wallet.app.service.AccountService;

@TestInstance(value = Lifecycle.PER_CLASS)
public class AccountRepositoryTest {
    AccountService serivce = new AccountService();
    Account accountTest;

    @BeforeAll
    public void before() {
        accountTest = serivce.saveAccount(new Account( "Account 1", "Bank", 1));
    }

    @AfterAll
    public void after() {
        serivce.removeById(accountTest.getId());
    }

    @Test
    void save() {
        assertEquals(accountTest.getName(), serivce.getAccountById(accountTest.getId()).getName());
    }
    
    @Test
    void getById() {
        assertNotNull(serivce.getAccountById(accountTest.getId()));
    }
}
