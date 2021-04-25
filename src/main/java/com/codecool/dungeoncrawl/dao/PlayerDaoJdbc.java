package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, x, y, hp, attack, armor, defence) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getX());
            statement.setInt(3, player.getY());
            statement.setInt(4, player.getHp());
            statement.setInt(5, player.getAttack());
            statement.setInt(6, player.getArmor());
            statement.setInt(7, player.getDefense());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET x=?, y=?, hp=?, attack=?, armor=?, defence=? WHERE player_name=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, player.getX());
            statement.setInt(2, player.getY());
            statement.setInt(3, player.getHp());
            statement.setInt(4, player.getAttack());
            statement.setInt(5, player.getArmor());
            statement.setInt(6, player.getDefense());
            statement.setString(7, player.getPlayerName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PlayerModel get(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,player_name,x,y,hp,attack,armor,defence FROM player WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String player_name = resultSet.getString(2);
                int x = resultSet.getInt(3);
                int y = resultSet.getInt(4);
                int hp = resultSet.getInt(5);
                int attack = resultSet.getInt(6);
                int armor = resultSet.getInt(7);
                int defence = resultSet.getInt(8);

                PlayerModel player = new PlayerModel(player_name, x, y, hp, attack, armor, defence);
                player.setId(id);

                return player;

            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,player_name,x,y,hp,attack,armor,defence FROM player";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<PlayerModel> allPlayers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String player_name = resultSet.getString(2);
                int x = resultSet.getInt(3);
                int y = resultSet.getInt(4);
                int hp = resultSet.getInt(5);
                int attack = resultSet.getInt(6);
                int armor = resultSet.getInt(7);
                int defence = resultSet.getInt(8);

                PlayerModel player = new PlayerModel(player_name, x, y, hp, attack, armor, defence);
                player.setId(id);
                allPlayers.add(player);
            }
            return allPlayers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PlayerModel get(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,player_name,x,y,hp,attack,armor,defence FROM player WHERE player_name=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String player_name = resultSet.getString(2);
                int x = resultSet.getInt(3);
                int y = resultSet.getInt(4);
                int hp = resultSet.getInt(5);
                int attack = resultSet.getInt(6);
                int armor = resultSet.getInt(7);
                int defence = resultSet.getInt(8);

                PlayerModel player = new PlayerModel(player_name, x, y, hp, attack, armor, defence);
                player.setId(id);

                return player;

            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
