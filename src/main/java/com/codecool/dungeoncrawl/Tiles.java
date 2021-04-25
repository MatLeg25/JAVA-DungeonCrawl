package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0, 29));
        tileMap.put("shield", new Tile(5, 26));
        tileMap.put("armor", new Tile(0, 23));
        tileMap.put("locked door", new Tile(10, 9));
        tileMap.put("door", new Tile(16, 8));
        tileMap.put("bad_door", new Tile(31, 7));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("health potion", new Tile(18, 25));
        tileMap.put("attack potion", new Tile(16, 30));
        tileMap.put("defense potion", new Tile(17, 25));
        tileMap.put("ring", new Tile(12, 28));
        tileMap.put("helmet", new Tile(3, 22));
        tileMap.put("gauntlet", new Tile(9, 22));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("dog", new Tile(21, 8));
        tileMap.put("game over", new Tile(18,24));
        tileMap.put("portal", new Tile(1, 9));
        tileMap.put("forrest", new Tile(3, 1));
        tileMap.put("wall1", new Tile(13, 16));
        tileMap.put("forrest_floor", new Tile(1, 0));
        tileMap.put("win", new Tile(12,24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
