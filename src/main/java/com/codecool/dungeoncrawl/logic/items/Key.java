package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {

    private static int count = 0;
    private final int id;


    public Key(Cell cell) {
        super(cell);
        this.id = count++;
    }

    public Key(int id, String name) {
        super(name);
        this.id = id;
    }

    public static void setCount(int count) {
        Key.count = count;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
