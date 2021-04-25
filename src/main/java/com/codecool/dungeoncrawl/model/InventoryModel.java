package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;

public class InventoryModel extends BaseModel {
    private String inventory;
    private final int playerId;


    public InventoryModel(PlayerModel playerModel, Inventory inventory) {
        this.inventory = inventory.toString();
        this.playerId = playerModel.getId();
    }

    public InventoryModel(int playerId, String inventory) {
        this.inventory = inventory;
        this.playerId = playerId;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public int getPlayerId() {
        return playerId;
    }

}
