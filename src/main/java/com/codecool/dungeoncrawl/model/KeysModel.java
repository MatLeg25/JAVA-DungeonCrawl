package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.items.Inventory;

public class KeysModel extends BaseModel {
    private final String keysIds;
    private final int inventoryId;

    public KeysModel(InventoryModel inventoryModel, Inventory inventory) {
        this.keysIds = inventory.getKeyIdsAsString(inventory.getKeysIds());
        this.inventoryId = inventoryModel.getId();
    }

    public KeysModel(int inventoryId, String keysIds) {
        this.keysIds = keysIds;
        this.inventoryId = inventoryId;
    }

    public String getKeysIds() {
        return keysIds;
    }

    public int getInventoryId() {
        return inventoryId;
    }
}
