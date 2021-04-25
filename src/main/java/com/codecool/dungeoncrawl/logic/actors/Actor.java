package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.battle.Battle;

public abstract class Actor implements Drawable {
    protected Cell cell;
    private int health;
    private int attack;
    private int armor;
    private int defence;
    private boolean playerIsDead = false;
    private boolean isNextLevel = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {

        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType nextCellType = nextCell.getType();
//        Battle battle = new Battle();

        if (((nextCellType.equals(CellType.FLOOR) || nextCellType.equals(CellType.FFLOOR) || nextCellType.equals(CellType.DOOR) || nextCellType.equals(CellType.PORTAL)) || nextCellType.equals(CellType.WIN)) && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        //if player is around enemy, && - to prevent fight between enemies
        if (nextCellType.equals(CellType.NPC) && this.getClass().equals(Player.class)) {
            Battle battle = new Battle();
            battle.fight(this, nextCell.getActor());
        }
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean isPositionInGameArea(int[] nextPosition) {
        int GAME_SIZE_X = 53;
        int GAME_SIZE_Y = 20;

        if(this.getX()+nextPosition[0] >=0 && this.getX()+nextPosition[0] < GAME_SIZE_X) {
            return this.getY() + nextPosition[1] >= 0 && this.getY() + nextPosition[1] < GAME_SIZE_Y;
        }
        return false;
    }

    public Actor checkIsPlayerAround(Actor actor) {
        //check if "actor" is close to player (standing on cell around)
        for(int i=-1;i<2;i++) {
            for(int j=-1;j<2;j++) {
                if(actor.getCell().getNeighbor(i,j).getActor() instanceof Player) {
                    return actor.getCell().getNeighbor(i,j).getActor();
                }
            }
        }
        return null;
    }

    public boolean isPlayerIsDead() {
        return playerIsDead;
    }

    public void setPlayerIsDead(boolean playerIsDead) {
        this.playerIsDead = playerIsDead;
    }

    public boolean isNextLevel() {
        return isNextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        isNextLevel = nextLevel;
    }

    public void move() {}
}

