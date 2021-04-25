package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item{
    private final static int ARMOR_BOOST = 25;

    public Helmet(Cell cell) {
        super(cell);
        this.setArmorBoost(ARMOR_BOOST);
    }

    public Helmet(String name) {
        super(name);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }
}
