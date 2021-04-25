package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    WALL("wall"),
    FLOOR("floor"),
    NPC("npc"),
    LOCKEDDOOR("locked door"),
    DOOR("door"),
    BAD_DOOR("bad_door"),
    GAMEOVER("game over"),
    PORTAL("portal"),
    FORREST("forrest"),
    WALL1("wall1"),
    FFLOOR("forrest_floor"),
    WIN("win");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
