package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;


public class Portal implements Drawable {
    private static int count = 0;
    private final int id;


    public Portal(Cell cell) {
        cell.setPortal(this);
        this.id = count++;
    }
    public int getId() {
        return id;
    }

    @Override
    public String getTileName() {
        return "portal";
    }
}
