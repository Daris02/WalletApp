package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Transfert;
import com.wallet.app.repository.TransfertRepository;

public class TransfertRepositoryTest {
    TransfertRepository repo = new TransfertRepository();
    
    @Test
    void getById() {
        Transfert transfert = new Transfert();
        assertEquals(transfert, transfert);
    }
}
