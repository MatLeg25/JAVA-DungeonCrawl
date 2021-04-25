package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class PlayerModel extends BaseModel {
    private final String playerName;
    private final int hp;
    private int x;
    private final int y;
    private final int attack;
    private int armor;
    private final int defence;

    public PlayerModel(String playerName, int x, int y, int hp, int attack, int armor, int defence) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.attack = attack;
        this.armor = armor;
        this.defence = defence;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.attack = player.getAttack();
        this.armor = player.getArmor();
        this.defence = player.getDefence();

    }

    public String getPlayerName() {
        return playerName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }


    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDefense() {
        return defence;
    }

}
