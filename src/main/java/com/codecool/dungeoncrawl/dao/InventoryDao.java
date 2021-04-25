package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;


import java.util.List;

public interface InventoryDao {
    void add(InventoryModel inventory);

    void update(InventoryModel inventory);

    InventoryModel get(int id);

    List<InventoryModel> getAll();

    int getInventoryId(int playerId);

}