package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDaoJdbc implements InventoryDao {
    private final DataSource dataSource;

    public InventoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(InventoryModel inventory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO inventory (inventory, player_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, inventory.getInventory());
            statement.setInt(2, inventory.getPlayerId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            inventory.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InventoryModel inventory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE inventory SET inventory=? WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, inventory.getInventory());
            statement.setInt(2, inventory.getPlayerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public InventoryModel get(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,inventory,player_id FROM inventory WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String inventory = resultSet.getString(2);
                int playerId = resultSet.getInt(3);

                InventoryModel inv = new InventoryModel(id, inventory);
                inv.setId(playerId);

                return inv;

            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InventoryModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,inventory,player_id FROM inventory";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<InventoryModel> allInventories = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String inventory = resultSet.getString(2);
                int player_id = resultSet.getInt(3);

                InventoryModel inv = new InventoryModel(player_id, inventory);
                inv.setId(id);
                allInventories.add(inv);
            }
            return allInventories;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getInventoryId(int playerId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql1 = "SELECT id FROM inventory WHERE player_id=?";

            PreparedStatement statement = conn.prepareStatement(sql1);
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("ID not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
