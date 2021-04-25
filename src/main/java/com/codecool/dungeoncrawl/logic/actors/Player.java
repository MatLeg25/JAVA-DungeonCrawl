package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.items.*;

public class Player extends Actor {
    private Inventory inventory = new Inventory();
    private final static int START_HEALTH = 100;
    private final static int START_ARMOR = 0;
    private final static int START_ATTACK = 20;
    private final static int START_DEFENSE = 0;
    private String name = "";
    private final static int[] currentPlayerPosition = {-1, -1};
    private boolean isWin = false;

    public Player(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setArmor(START_ARMOR);
        this.setAttack(START_ATTACK);
        this.setDefence(START_DEFENSE);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);

        setCurrentPlayerPosition(this.getX(), this.getY());

        Cell nextCell = cell.getNeighbor(dx, dy);

        if (cell.getItem() != null) {
            pickUpItem();
            checkHeal();
            cell.setItem(null);
        } else if (nextCell.getType().equals(CellType.LOCKEDDOOR)) {
            checkDoor(dx, dy);
        } else if (cell.getType().equals(CellType.PORTAL)) {
            setNextLevel(true);
        } else if (cell.getType().equals(CellType.WIN)) {
            setWin(true);
        }
    }

    public void pickUpItem() {
        if (cell.getItem().getClass().getSimpleName().equals("Sword")
                || cell.getItem().getClass().getSimpleName().equals("Shield")
                || cell.getItem().getClass().getSimpleName().equals("Armor")
                || cell.getItem().getClass().getSimpleName().equals("Key")
                || cell.getItem().getClass().getSimpleName().equals("Gauntlet")
                || cell.getItem().getClass().getSimpleName().equals("Helmet")
                || cell.getItem().getClass().getSimpleName().equals("Ring")
        ) {
            inventory.addItem(cell.getItem());
            inventory.displayInventory();
            this.setAttack(this.getAttack() + cell.getItem().getAttackBoost());
            this.setDefence(this.getDefence() + cell.getItem().getDefenceBoost());
            this.setArmor(this.getArmor() + cell.getItem().getArmorBoost());
            cell.setItem(null);
        }
    }

    public void checkHeal() {
        if (cell.getItem() != null) {
            if (cell.getItem().getClass().getSimpleName().equals("HealthPotion")) {
                if (this.getHealth() < 50) {
                    this.setHealth(this.getHealth() + 50);
                } else this.setHealth(100);
            }
        }
    }

    public void checkDoor(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        for (Key key : inventory.getKeysIds()) {
            if (key.getId() == nextCell.getDoor().getId() || 1 == 1) { //add "|| 1==1" to temp solve for issue with key
                nextCell.setType(CellType.DOOR);
                inventory.removeItem(key);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void checkInventory(String inventory, String keysID) {
        if (inventory.length() > 1) {
            setInventory(inventory, keysID);
        }
    }

    public void setInventory(String inventory, String keysID) {
        String[] keys = {""};
        Item item;
        int index = 0;
        if (keysID.length() > 0) {
            keys = keysID.split(" ");
        }

        Inventory loadedInventory = new Inventory();
        String[] inventorySplitted = inventory.split(System.lineSeparator());

        for (String itemFullName : inventorySplitted) {
            String itemType = itemFullName.split("-")[1];

            switch (itemType) {
                case "Key":
                    item = new Key(Integer.parseInt(keys[index]), itemFullName);
                    loadedInventory.addItem(item);
                    index++;
                    break;
                case "Shield":
                    item = new Shield(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "Sword":
                    item = new Sword(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "Armor":
                    item = new Armor(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "Gauntlet":
                    item = new Gauntlet(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "Helmet":
                    item = new Helmet(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "Ring":
                    item = new Ring(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "AttackPotion":
                    item = new AttackPotion(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "DefensePotion":
                    item = new DefensePotion(itemFullName);
                    loadedInventory.addItem(item);
                    break;
                case "HealthPotion":
                    item = new HealthPotion(itemFullName);
                    loadedInventory.addItem(item);
                    break;
            }
        }
        this.inventory = loadedInventory;

    }


    public void setCurrentPlayerPosition(int dx, int dy) {
        currentPlayerPosition[0] = dx;
        currentPlayerPosition[1] = dy;
    }

    public static int[] getCurrentPlayerPosition() {
        return currentPlayerPosition;
    }

    public String getTileName() {
        return "player";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
