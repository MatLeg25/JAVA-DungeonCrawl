package com.codecool.dungeoncrawl.logic.battle;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Battle {

    public Battle() {
    }

    public void fight(Actor player, Actor enemy) {
        int playerHealth = getEnemyAttackResult(player, enemy);
        int enemyHealth = getPlayerAttackResult(player, enemy);

        if (playerHealth <= 0) {
            player.getCell().setType(CellType.GAMEOVER);
            player.setPlayerIsDead(true);
            player.getCell().setActor(null);


        }
        else if (enemyHealth <= 0) {
            enemy.getCell().setActor(null);
            enemy.getCell().setType(CellType.FLOOR);
        }
    }


    public int getPlayerAttackResult(Actor player, Actor enemy) {
        int playerAttack = player.getAttack();
        int enemyHealth = enemy.getHealth();

        enemy.setHealth(enemyHealth - playerAttack);
        return enemyHealth - playerAttack;
    }

    public int getEnemyAttackResult(Actor player, Actor enemy) {
        int enemyAttack = enemy.getAttack();
        int playerHealth = player.getHealth();
        int playerArmor = player.getArmor();
        int playerDefence = player.getDefence();

        if (enemyAttack >= playerDefence) {
            player.setDefence(0);
            if (enemyAttack >= playerDefence + playerArmor) {
                player.setArmor(0);
                player.setHealth(playerHealth + playerArmor + playerDefence - enemyAttack);
                return  playerHealth + playerArmor + playerDefence - enemyAttack;
            }
            else {
                player.setArmor(playerArmor + playerDefence - enemyAttack);
                player.setHealth(playerHealth);
                return playerHealth;
            }
        }
        else {
            player.setDefence(playerDefence - enemyAttack);
        }
        player.setHealth(playerHealth);
        return playerHealth;
    }
}

