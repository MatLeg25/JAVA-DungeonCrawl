
package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.Door;
import com.codecool.dungeoncrawl.logic.items.Key;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;

import static com.codecool.dungeoncrawl.logic.actors.Dog.isDogHelpAvailable;


public class Main extends Application {
    String playerNamePlaceholder;
    GameMap map = MapLoader.loadMap("/map.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label armorLabel = new Label();
    Label attackLabel = new Label();
    Label defenseLabel = new Label();
    private Thread charactersMovementThread;

    GameDatabaseManager dbManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setupDbManager();

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        ui.add(new Label("Armor : "), 0, 1);
        ui.add(armorLabel, 1, 1);

        ui.add(new Label("Attack: "), 0, 2);
        ui.add(attackLabel, 1, 2);

        ui.add(new Label("Defence: "), 0, 3);
        ui.add(defenseLabel, 1, 3);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

        startCharactersMovement(); // TURN OFF MOVE 1/3

        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        StartWindow startWindow = new StartWindow();
        playerNamePlaceholder = startWindow.getName();
        map.getPlayer().setName(playerNamePlaceholder);
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (map.getPlayer().isNextLevel()) {
            map = MapLoader.loadMap("/map2.txt");
            startCharactersMovement();// TURN OFF MOVE 2/3
        }

        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
            case I:
                if (map.getPlayer().getInventory().toString().equals("")) {
                    new InventoryWindow("Inventory EMPTY");
                } else {
                    new InventoryWindow(map.getPlayer().getInventory().toString());
                }
            case G: //if player is close to dog = player can send dog to the target
                if (isDogHelpAvailable()) {
                    map.getAdvancedDog().setExtraMove(true);
                }
                break;
            case B: //dog is following to player
                map.getAdvancedDog().setExtraMove(false);
                break;
            case S:
                saveGame();
                break;
            case R:
                loadGame();
                break;
            case E:
                exit();
                break;
        }
    }

    public synchronized void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        armorLabel.setText("" + map.getPlayer().getArmor());
        attackLabel.setText("" + map.getPlayer().getAttack());
        defenseLabel.setText("" + map.getPlayer().getDefence());
        if (map.getPlayer().isPlayerIsDead()) {
            new EndWindow();
        } else if (map.getPlayer().isWin()) {
            new WinWindow();
        }
    }

    public void startCharactersMovement() {
        if (charactersMovementThread != null) {
            charactersMovementThread.stop(); //could by done by .interrupt() = required managing with Threads
        }
        //async move of enemy: Thread - by AutoMove class
        Runnable enemiesAll = new AutoMove(this, map.getCharacters());
        charactersMovementThread = new Thread(enemiesAll);
        charactersMovementThread.start();
    }

    private void setupDbManager() {
        dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private void saveGame() {
        Player player = map.getPlayer();
        map.getPlayer().setName(playerNamePlaceholder);
        String currentMapState = MapWriter.getSavedMap(map);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //DATE format:  2021-03-24 16:48:05.591
        dbManager.savePlayerGame(player, currentMapState, timestamp);
        System.out.println("Game saved at " + timestamp);
    }

    private void loadGame() {
        Key.setCount(0);
        Door.setCount(0);
        map.getPlayer().setName(playerNamePlaceholder);
        System.out.print(System.lineSeparator()+"Loading game...");
        if (dbManager.loadPlayerGame(map.getPlayer())) {
            String loadedMap = dbManager.getReadGameState().getCurrentMap();
            map = MapLoader.loadMap2(loadedMap);
            String keys = dbManager.getReadKeys().getKeysIds();
            map.getPlayer().setHealth(dbManager.getReadPlayer().getHp());
            map.getPlayer().setAttack(dbManager.getReadPlayer().getAttack());
            map.getPlayer().setArmor(dbManager.getReadPlayer().getArmor());
            map.getPlayer().setDefence(dbManager.getReadPlayer().getDefense());
            String inventory = dbManager.getReadInventory().getInventory();
            map.getPlayer().checkInventory(inventory, keys);
        }
        refresh();
        startCharactersMovement();// TURN OFF MOVE 3/3
        System.out.println(" ...END!");
    }
}