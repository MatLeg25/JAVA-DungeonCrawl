package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Random;

public abstract class Item implements Drawable {

    private static final String[] POSSIBLE_ADJECTIVES = {
            "Vagluocium",
            "Weshourhil",
            "Bostralium",
            "Ogreocium",
            "Scasten",
            "Breatrium",
            "Kruyrium",
            "Nogryx",
            "Guchine",
            "Pawhum",
            "Iowhenese",
            "Nefloethil",
            "Iasteatine",
            "Odriynor",
            "Thoytine",
            "Spoirhil",
            "Cluesium",
            "Joblese",
            "Sablium",
            "TuspianEshaotium",
            "Zescautine",
            "Vafroesten",
            "Hoprolium",
            "Criethil",
            "Drudian",
            "Crelium",
            "Othum",
            "Kachite",
            "Jetrite",
    };
    protected String name;
    private int attackBoost = 0;
    private int armorBoost = 0;
    private int defenceBoost = 0;
    protected static final Random RANDOM = new Random();

    public Item(Cell cell) {
        this.name = generateName();
        cell.setItem(this);
    }

    public Item(String name) {
        this.name = name;
    }

    public Item() { }

    protected String generateName(){
        String adjective = POSSIBLE_ADJECTIVES[RANDOM.nextInt(POSSIBLE_ADJECTIVES.length)];
        String itemType = this.getClass().getSimpleName();

        return adjective + "-" + itemType;
    }

    public String getName() {
        return name;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public void setAttackBoost(int attackBoost) {
        this.attackBoost = attackBoost;
    }

    public int getArmorBoost() {
        return armorBoost;
    }

    public void setArmorBoost(int armorBoost) {
        this.armorBoost = armorBoost;
    }

    public int getDefenceBoost() {
        return defenceBoost;
    }

    public void setDefenceBoost(int defenceBoost) {
        this.defenceBoost = defenceBoost;
    }
}
