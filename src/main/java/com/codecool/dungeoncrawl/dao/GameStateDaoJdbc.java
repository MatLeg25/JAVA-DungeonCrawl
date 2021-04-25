package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateDaoJdbc implements GameStateDao {
    private DataSource dataSource;

    public GameStateDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO game_state (current_map, saved_at, player_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            state.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameState state) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE game_state SET current_map=?, saved_at=? WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, state.getCurrentMap());
            statement.setTimestamp(2, state.getSavedAt());
            statement.setInt(3, state.getPlayer().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameState get(int player_id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,current_map,saved_at,player_id FROM game_state WHERE player_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt(1);
                String current_map = resultSet.getString(2);
                Timestamp saved_at = resultSet.getTimestamp(3);
                int playerId = resultSet.getInt(4);

                GameState gameState = new GameState(current_map,saved_at,playerId);
                gameState.setId(id);

                return gameState;

            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GameState> getAll() { //NOT VERIFIED
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id,current_map,saved_at,player_id FROM game_state";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            List<GameState> allGameStates = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String current_map = resultSet.getString(2);
                Timestamp saved_at = resultSet.getTimestamp(3);
                int player_id = resultSet.getInt(4);

                GameState gameState = new GameState(current_map,saved_at,player_id);
                gameState.setId(id);
                allGameStates.add(gameState);
            }
            return allGameStates;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
