
package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Dog;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case ',':
                            cell.setType(CellType.FFLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'm':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.LOCKEDDOOR);
                            new Door(cell);
                            break;
                        case 'o':
                            cell.setType(CellType.DOOR);
                            new Door(cell);
                            break;
                        case '!':
                            cell.setType(CellType.BAD_DOOR);
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new Helmet(cell);
                            break;
                        case '1':
                            cell.setType(CellType.FLOOR);
                            new AttackPotion(cell);
                            break;
                        case '2':
                            cell.setType(CellType.FLOOR);
                            new HealthPotion(cell);
                            break;
                        case '3':
                            cell.setType(CellType.FLOOR);
                            new DefensePotion(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Gauntlet(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new Ring(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            map.setGhosts(new Ghost(cell));
                            break;
                        case 'f':
                            cell.setType(CellType.FLOOR);
                            map.setDog(new Dog(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.PORTAL);
                            new Portal(cell);
                            break;
                        case '&':
                            cell.setType(CellType.WALL1);
                            break;
                        case '^':
                            cell.setType(CellType.FORREST);
                            break;
                        case '$':
                            cell.setType(CellType.WIN);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

    public static GameMap loadMap2(String loadedMap) {
        String[] mapTxtSplitted = loadedMap.split(System.lineSeparator()); //map as Array of Strings
        String[] firstLine = mapTxtSplitted[0].split(" "); //first line with size of map (see map.txt)
        int width = Integer.parseInt(firstLine[0]);
        int height = Integer.parseInt(firstLine[1]);
        //create Array of String with size corresponding to real game size (-first line)
        String[] onlyMap = new String[height];
        System.arraycopy(mapTxtSplitted, 1, onlyMap, 0, mapTxtSplitted.length - 1);

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = onlyMap[y];
            for (int x = 0; x < width; x++) {
                Cell cell = map.getCell(x, y);
                switch (line.charAt(x)) {
                    case ' ':
                        cell.setType(CellType.EMPTY);
                        break;
                    case '#':
                        cell.setType(CellType.WALL);
                        break;
                    case '.':
                        cell.setType(CellType.FLOOR);
                        break;
                    case ',':
                        cell.setType(CellType.FFLOOR);
                        break;
                    case 's':
                        cell.setType(CellType.FLOOR);
                        new Skeleton(cell);
                        break;
                    case '@':
                        cell.setType(CellType.FLOOR);
                        map.setPlayer(new Player(cell));
                        break;
                    case 'm':
                        cell.setType(CellType.FLOOR);
                        new Sword(cell);
                        break;
                    case 't':
                        cell.setType(CellType.FLOOR);
                        new Shield(cell);
                        break;
                    case 'a':
                        cell.setType(CellType.FLOOR);
                        new Armor(cell);
                        break;
                    case 'k':
                        cell.setType(CellType.FLOOR);
                        new Key(cell);
                        break;
                    case 'd':
                        cell.setType(CellType.LOCKEDDOOR);
                        new Door(cell);
                        break;
                    case 'o':
                        cell.setType(CellType.DOOR);
                        new Door(cell);
                        break;
                    case '!':
                        cell.setType(CellType.BAD_DOOR);
                        break;
                    case 'h':
                        cell.setType(CellType.FLOOR);
                        new Helmet(cell);
                        break;
                    case '1':
                        cell.setType(CellType.FLOOR);
                        new AttackPotion(cell);
                        break;
                    case '2':
                        cell.setType(CellType.FLOOR);
                        new HealthPotion(cell);
                        break;
                    case '3':
                        cell.setType(CellType.FLOOR);
                        new DefensePotion(cell);
                        break;
                    case 'g':
                        cell.setType(CellType.FLOOR);
                        new Gauntlet(cell);
                        break;
                    case 'r':
                        cell.setType(CellType.FLOOR);
                        new Ring(cell);
                        break;
                    case 'x':
                        cell.setType(CellType.FLOOR);
                        map.setGhosts(new Ghost(cell));
                        break;
                    case 'f':
                        cell.setType(CellType.FLOOR);
                        map.setDog(new Dog(cell));
                        break;
                    case 'p':
                        cell.setType(CellType.PORTAL);
                        new Portal(cell);
                        break;
                    case '&':
                        cell.setType(CellType.WALL1);
                        break;
                    case '^':
                        cell.setType(CellType.FORREST);
                        break;
                    case '$':
                        cell.setType(CellType.WIN);
                        break;
                    default:
                        throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                }
            }
        }
        return map;
    }
}
