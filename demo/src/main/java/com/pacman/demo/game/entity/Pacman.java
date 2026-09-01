package com.pacman.demo.game.entity;
import com.pacman.demo.game.state.EntityState;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Pacman extends Character {
    private int lives=3;
    private int score=0;
    private int direction=1;
    private int nextDirection=1;
    public Pacman(int x, int y, int speed){
        super(x,y,speed);
    }
    @Override
    public void move(Map map){
        updateTimers();              
        updateDirectionAtGridCell(map);
        performMove(map);
    }

    private void updateDirectionAtGridCell(Map map) {
        boolean atGridCell = (getX() % 32 == 0 && getY() % 32 == 0);
        if (!atGridCell) return;

        boolean isReversing = nextDirection == (direction + 2) % 4;
        if (isReversing) {
            direction = nextDirection;
            return;
        }
        int ndx = getDx(nextDirection);
        int ndy = getDy(nextDirection);
        boolean canTurn = hasFlag(EntityState.WALL_HACK)
                || !map.isWall(getX() + ndx * getSpeed(), getY() + ndy * getSpeed());
        if (!canTurn) return;

        direction = nextDirection;
    }

    private void performMove(Map map) {
        int dx = getDx(direction);
        int dy = getDy(direction);
        int newX = getX() + dx * getSpeed();
        int newY = getY() + dy * getSpeed();

        boolean canMove = hasFlag(EntityState.WALL_HACK) || !map.isWall(newX, newY);

        if (canMove) {
            setX(newX);
            setY(newY);
            setFlag(EntityState.MOVING);
            clearFlag(EntityState.WAS_STUCK);
            return;
        }

        clearFlag(EntityState.MOVING);
        snapToGridIfNeeded(dx, dy);
    }

    private void snapToGridIfNeeded(int dx, int dy) {
        if (hasFlag(EntityState.WAS_STUCK)) return;

        if (dx != 0) {
            int remainder = getX() % 32;
            setX(remainder > 16 ? getX() + (32 - remainder) : getX() - remainder);
        }
        if (dy != 0) {
            int remainder = getY() % 32;
            setY(remainder > 16 ? getY() + (32 - remainder) : getY() - remainder);
        }
        setFlag(EntityState.WAS_STUCK);
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


    public void setNextDirection(int dir) {
        this.nextDirection = dir;
    }

    private int getDx(int dir) {
        switch (dir) {
            case 1: return 1;
            case 3: return -1;
            default: return 0;
        }
    }
    private int getDy(int dir) {
        switch (dir) {
            case 2: return 1;
            case 0: return -1;
            default: return 0;
        }
    }
    public void addScore(int point) { score += point; }

}
