package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.List;

public class AutoMove implements Runnable {

    private final List<Actor> characters;
    private final Main main;

    public AutoMove(Main main, List<Actor> characters) {
        this.characters = characters;
        this.main = main;
    }

    @Override
    public void run() {
        while (characters.size() > 0) {
            //System.out.println(Thread.currentThread().getName()+" autoMove");
            checkHealth();
            moveCharacters();
        }
    }

    private void moveCharacters() {
        try {
            Thread.sleep(500);
            for (Actor actor : characters) {
                actor.move();
                main.refresh();
            }
        } catch (InterruptedException e) { //to be updated, better solution than .stop()
            System.out.println("End of thread");
            Thread.currentThread().interrupt();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkHealth() {
        for (Actor actor : characters) {
            if (actor.getHealth() <= 0) {
                characters.remove(actor);
                break;
            }
        }
    }
}


