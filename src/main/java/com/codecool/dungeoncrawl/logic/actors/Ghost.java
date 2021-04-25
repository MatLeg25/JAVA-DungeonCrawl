package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.battle.Battle;

import java.util.Random;

public class Ghost extends Actor {
    private final static int START_HEALTH = 50;
    private final static int START_ATTACK = 5;
    private final Random random = new Random();

    public Ghost(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move() {
        //go to random position in range +-3 cell
        int randX = random.nextInt(6) - 3; //-3,3
        int randY = random.nextInt(6) - 3; //-3,3


        if (super.isPositionInGameArea(new int[]{randX, randY})) {
            super.move(randX, randY);
        }
        //if met player, start fight
        Player detectedPlayer = (Player) super.checkIsPlayerAround(this);
        if (detectedPlayer != null) {
            Battle battle = new Battle();
            battle.fight(detectedPlayer, this);
        }
    }
}
