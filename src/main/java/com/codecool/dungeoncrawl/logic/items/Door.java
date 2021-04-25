package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;


public class Door implements Drawable {
    private static int count = 0;
    private final int id;

    public Door(Cell cell) {
        cell.setDoor(this);
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public static void setCount(int count) {
        Door.count = count;
    }

    @Override
    public String getTileName() {
        return "door";
    }
}
