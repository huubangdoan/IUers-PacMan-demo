package com.pacman.demo.game.entity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.pacman.demo.game.behaviour.Moveable;
import com.pacman.demo.game.state.EntityState;
import com.pacman.demo.game.map.GameMap;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class Character implements Moveable {
    private int x,y;
    private int speed;
    private int flags=0; //EntityState
    protected int direction=1;
    protected int nextDirection=1;
    private final Map<Integer, Long> timers = new HashMap<>(); //DSA
    public Character(int x, int y,  int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
    }

    // state flag
    public void setFlag(int flag) {
        flags |= flag;
    }
    public void clearFlag(int flag) {
        flags &= ~flag;
    }
    public boolean hasFlag(int flag) {
        return (flags & flag) != 0;
    }
    public void setFlag(int flag, boolean value) {
        if (value) setFlag(flag);
        else clearFlag(flag);
    }


    //Time count
    public void setTimedFlag(int flag, long durationMillis) {
        setFlag(flag);
        timers.put(flag, System.currentTimeMillis() + durationMillis);
    }
    public void updateTimers() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Long>> it = timers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Long> entry = it.next();
            if (now >= entry.getValue()) {
                flags &= ~entry.getKey(); 
                it.remove();             
            }
        }
    }
    public long getRemainingTime(int flag) {
        Long endTime = timers.get(flag);
        if (endTime == null) return 0;
        return Math.max(endTime - System.currentTimeMillis(), 0);
    }

    //move
    @Override
    public void move(GameMap map){
        updateTimers();
        if (isImmobilized()) return;
        updateDirection(map);
        performStep(map);
    }
    //abstract
    public abstract void updateDirection(GameMap map);
    public void performStep(GameMap map) {
        int dx = getDx(direction);
        int dy = getDy(direction);
        int newX = getX() + dx * getSpeed();
        int newY = getY() + dy * getSpeed();

        if (!map.isWall(newX, newY)) {
            setX(newX);
            setY(newY);
            setFlag(EntityState.MOVING);
            clearFlag(EntityState.WAS_STUCK);
        } else {
            clearFlag(EntityState.MOVING);
            setFlag(EntityState.WAS_STUCK);
        }
    }
    @Override
    public boolean isImmobilized() {
        return hasFlag(EntityState.IMMOBILIZE_MASK);
    }
    @Override
    public boolean isMoving() {
        return hasFlag(EntityState.MOVING);
    }
    public void setNextDirection(int dir) {
        this.nextDirection = dir;
    }
    public int getDx(int dir) {
        switch (dir) {
            case 1: return 1;
            case 3: return -1;
            default: return 0;
        }
    }
    public int getDy(int dir) {
        switch (dir) {
            case 2: return 1;
            case 0: return -1;
            default: return 0;
        }
    }
    public boolean isAtGridCell() {
        return x % 32 == 0 && y % 32 == 0;
    }
    public int snapToNearestGrid(int value) {
    int remainder = value % 32;
    if (remainder > 32 / 2) {
        return value + (32 - remainder);
    }
    return value - remainder;
    } 
}

