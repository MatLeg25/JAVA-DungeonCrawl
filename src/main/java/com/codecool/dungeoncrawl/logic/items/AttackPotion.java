package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class AttackPotion extends Item{
    private final static int ATTACK_BOOST = 20;
    private final static int BOOST_DURATION = 0;


    public AttackPotion(Cell cell) {
        super(cell);
        this.setAttackBoost(ATTACK_BOOST);
    }

    public AttackPotion(String name) {
        super(name);
    }

    @Override
    public String getTileName() {
        return "attack potion";
    }
}
