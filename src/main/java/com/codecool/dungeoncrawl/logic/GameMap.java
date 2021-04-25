package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Dog;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Door;
import com.codecool.dungeoncrawl.logic.items.Item;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private final int width;
    private final int height;
    private final Cell[][] cells;

    private Player player;
    private Item item;
    private Door door;
    private final ArrayList<Actor> ghosts = new ArrayList<>();
    private Dog dogAdvanced;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGhosts(Ghost ghost) {
        this.ghosts.add(ghost);
    }

    public ArrayList<Actor> getGhosts() {
        return ghosts;
    }

    public void setDog(Dog dog) {
        this.dogAdvanced = dog;
    }

    public Dog getAdvancedDog() {
        return dogAdvanced;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Actor> getCharacters() {
        List<Actor> enemies = new ArrayList<>();
        enemies.add(this.getAdvancedDog());
        enemies.addAll(this.getGhosts());
        return enemies;
    }
}
