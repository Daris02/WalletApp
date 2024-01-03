package config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.exception.DatabaseConnectionException;

public class ConnectionDBTest {
    @Test
    void connection_ok() {
        assertNotNull(ConnectionDB.createConnection());
    }
    
    @Test
    void connection_ko() {
        Assertions.assertThrows(DatabaseConnectionException.class, () -> ConnectionDB.createConnection(null, null, null));
    }
}
