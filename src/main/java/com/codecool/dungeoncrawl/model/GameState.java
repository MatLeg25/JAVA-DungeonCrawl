package com.codecool.dungeoncrawl.model;

import java.sql.Timestamp;

public class GameState extends BaseModel {
    private final Timestamp savedAt;  //changed Date to Timestamp to save Date with time
    private final String currentMap;
    private PlayerModel player;

    public GameState(String currentMap, Timestamp savedAt, PlayerModel player) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
    }

    public GameState(String currentMap, Timestamp savedAt, int player_ID) {
        this.currentMap = currentMap;
        this.savedAt = savedAt;
    }

    public Timestamp getSavedAt() {
        return savedAt;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
