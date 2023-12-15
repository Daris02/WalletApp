package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Category;

public class CategoryRepository implements Crud<Category> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public Category getById(String id) {
        String sql = "SELECT * FROM \"category\" WHERE id = " + id + ";";
        Category responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT  * FROM \"category\" ORDER BY name;";
        List<Category> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    )
                );
            }
            return responseSQL;

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
            saveAll.add(getByName(category.getName()));
        }
        return saveAll;
    }

    public Category getByName(String name) {
        String sql = "SELECT * FROM \"category\" WHERE name = " + name + ";";
        Category responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Category save(Category toSave) {
        String sql = "INSERT INTO \"category\" (name) VALUES " +
            "('" + toSave.getName() + "');";

        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
