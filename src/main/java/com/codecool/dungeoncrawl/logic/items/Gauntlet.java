package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gauntlet extends Item{
    private final static int ARMOR_BOOST = 10;
    public Gauntlet(Cell cell) {
        super(cell);
        this.setArmorBoost(ARMOR_BOOST);
    }

    public Gauntlet(String name) {
        super(name);
    }

    @Override
    public String getTileName() {
        return "gauntlet";
    }
}
