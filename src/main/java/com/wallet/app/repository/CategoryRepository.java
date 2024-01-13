package com.wallet.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Category;

public class CategoryRepository extends AutoCrud<Category, Integer> {

    @Override
    protected String getTableName() {
        return "category";
    }
    
    @Override
    protected Category mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Category(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> saveAll(List<Category> toSave) {
        List<Category> saveAll = new ArrayList<>();
        for (Category category : toSave) {
            save(category);
            saveAll.add(category);
        }
        return saveAll;
    }
}
