package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import static com.codecool.dungeoncrawl.logic.actors.Player.getCurrentPlayerPosition;

public class Dog extends Actor {
    private final static int START_HEALTH = 40;
    private final static int START_ATTACK = 10;
    private final boolean advancedMovement;
    private static boolean isClosePlayer = false;
    private boolean extraMove = false;
    private final int[] DOG_TARGET = {22,4};

    public Dog(Cell cell) {
        super(cell);
        this.setHealth(START_HEALTH);
        this.setAttack(START_ATTACK);
        this.advancedMovement = true;
    }

    @Override
    public String getTileName() {
        return "dog";
    }

    @Override
    public void move() {
        int[] dogPosition = {this.getX(), this.getY()};
        int[] stepToTargetPosition = getTargetPosition(dogPosition);

        if (super.isPositionInGameArea(stepToTargetPosition)) {
            super.move(stepToTargetPosition[0], stepToTargetPosition[1]);
        }
    }

    public int[] getTargetPosition(int[] dogPosition) {
        int[] targetPosition = getTarget();

        int distanceX = targetPosition[0] - dogPosition[0];
        int distanceY = targetPosition[1] - dogPosition[1];

        setIsCloseToPlayer(distanceX,distanceY);

        //To be updated to provide dog omit obstacles
        if(distanceX < 0) {return new int[]{-1,0};}
        if(distanceX > 0) {return new int[]{+1,0};}
        if(distanceY < 0) {return new int[]{0,-1};}
        if(distanceY > 0) {return new int[]{0,+1};}

        return new int[]{0,0};
    }

    private void setIsCloseToPlayer(int distanceX, int distanceY) {
        isClosePlayer = Math.abs(distanceX) < 2 && Math.abs(distanceY) < 2;
    }

    public boolean getEnemyType() {
        return this.advancedMovement;
    }

    public static boolean isDogHelpAvailable() {return isClosePlayer;}

    public void setExtraMove(boolean state) {
        this.extraMove = state;
    }

    public int[] getTarget() {
        if(!extraMove) {
            return getCurrentPlayerPosition();
        } else {
            return DOG_TARGET;
        }
    }
}


///DOG description:
// Dog is a friend - by default dog want move to player position.
// When player is close to dog (isClosePlayer=true), player can activate (press G) dog to follow to nextAIm = DOG_TARGET set as last point to win a game.
// BY press "B" player set "extraMove=false" - dog is following to player