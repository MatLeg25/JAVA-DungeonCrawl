package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.KeysModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeysDaoJdbc implements KeysDao {
    private final DataSource dataSource;

    public KeysDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(KeysModel keysModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO keys (keys, inventory_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, keysModel.getKeysIds());
            statement.setInt(2, keysModel.getInventoryId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            keysModel.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(KeysModel keysModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE keys SET keys=? WHERE inventory_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, keysModel.getKeysIds());
            statement.setInt(2, keysModel.getInventoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public KeysModel get(int inventoryId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, keys, inventory_id FROM keys WHERE inventory_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, inventoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new KeysModel(
                        resultSet.getInt(1),
                        resultSet.getString(2));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<KeysModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, keys, inventory_id FROM keys";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<KeysModel> keysModels = new ArrayList<>();

            while (resultSet.next()) {
                KeysModel keysModel = new KeysModel(
                        resultSet.getInt(1),
                        resultSet.getString(2));
            }
            return keysModels;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getKeysId(int inventoryId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM keys WHERE inventory_id=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, inventoryId);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("Key ID not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
