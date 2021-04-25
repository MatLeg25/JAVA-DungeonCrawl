package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Skeleton extends Actor {
    private final static int START_HEALTH = 40;
    private final static int START_ATTACK = 10;

    public Skeleton(Cell cell) {

        super(cell);
        cell.setType(CellType.NPC);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
