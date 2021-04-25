package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.InventoryModel;
import com.codecool.dungeoncrawl.model.KeysModel;

import java.util.List;

public interface KeysDao {
    void add(KeysModel keysModel);
    void update(KeysModel keysModel);
    KeysModel get(int id);
    List<KeysModel> getAll();
    int getKeysId(int id);
}
