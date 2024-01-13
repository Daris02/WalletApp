package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wallet.app.model.Category;
import com.wallet.app.repository.CategoryRepository;


public class CategoryRepositoryTest {
    CategoryRepository repo = new CategoryRepository();
    
    @Test
    void getById() {
        Category category = new Category(1, "Transfert");
        assertEquals(category, repo.getById(1));
    }
}
