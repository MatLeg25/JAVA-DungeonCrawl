package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Armor extends Item {
    private final static int ARMOR_BOOST = 50;



    public Armor(Cell cell) {
        super(cell);
        this.setArmorBoost(ARMOR_BOOST);
    }

    public Armor(String name) {
        super(name);
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
