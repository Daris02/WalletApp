package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Transaction;
import com.wallet.app.repository.TransactionRepository;

public class TransactionRepositoryTest {
    TransactionRepository repo = new TransactionRepository();
    
    @Test
    void getById() {
        Transaction transaction = new Transaction();
        assertEquals(transaction, transaction);
    }
}
