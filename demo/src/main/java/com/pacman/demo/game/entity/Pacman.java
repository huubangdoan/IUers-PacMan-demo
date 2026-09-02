package com.pacman.demo.game.entity;
import com.pacman.demo.game.state.EntityState;
import com.pacman.demo.game.map.GameMap;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Pacman extends Character {
    private int lives=3;
    private int score=0;
    public Pacman(int x, int y, int speed){
        super(x,y,speed);
    }

    //move
    @Override
    public void updateDirection(GameMap map) {
    if (!isAtGridCell()) return;

    boolean isReversing = nextDirection == (direction + 2) % 4;
    if (isReversing) {
        direction = nextDirection;
        return;
    }
    int ndx = getDx(nextDirection);
    int ndy = getDy(nextDirection);
    boolean canTurn = hasFlag(EntityState.WALL_HACK)
            || !map.isWall(getX() + ndx * getSpeed(), getY() + ndy * getSpeed());
    
    if (canTurn) direction = nextDirection;
    }


    //durian
    public boolean hasThorns() { return hasFlag(EntityState.HAS_THORNS); }
    public void setHasThorns(boolean value) { setFlag(EntityState.HAS_THORNS, value); }
    //watermelon
    public boolean hasWatermelon() { return hasFlag(EntityState.HAS_WATERMELON); }
    public void setHasWatermelon(boolean value) { setFlag(EntityState.HAS_WATERMELON, value); }
    //kiwi
    public void activeDisguise(long time) {
        setTimedFlag(EntityState.IS_DISGUISED, time); //format 10_000 
    }
    public boolean isDisguised() {return hasFlag(EntityState.IS_DISGUISED);}
    //apple
    public void activePowerup(long time) {
        setTimedFlag(EntityState.HAS_POWERUP, time);
    }
    public boolean isPowerup() {
        return hasFlag(EntityState.HAS_POWERUP);
    }
    //dragon fruit
    public void activeDragonMode(long time) {
        setTimedFlag(EntityState.DRAGON_MODE, time);
    }
    public boolean isDragonMode() {
        return hasFlag(EntityState.DRAGON_MODE);
    }
    //chilli
    public void activateChilliPower(long time) {
        setTimedFlag(EntityState.HAS_CHILLI, time);
        setSpeed(4);
        javax.swing.Timer chilliTimer = new javax.swing.Timer((int) time, e -> setSpeed(2));
        chilliTimer.setRepeats(false);
        chilliTimer.start();
    }

    public void addScore(int point) { score += point; }

}
