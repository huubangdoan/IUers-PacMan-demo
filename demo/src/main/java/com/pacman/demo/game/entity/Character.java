package com.pacman.demo.game.entity;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.pacman.demo.game.state.EntityState;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class Character {
    private int x,y;
    private int speed;
    private int flags=0; //EntityState
    private final Map<Integer, Long> timers = new HashMap<>();
    public Character(int x, int y,  int speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
    }
    public abstract void move(Map map);

    // turn on state
    public void setFlag(int flag) {
        flags |= flag;
    }
    //turn off state
    public void clearFlag(int flag) {
        flags &= ~flag;
    }
    //check state
    public boolean hasFlag(int flag) {
        return (flags & flag) != 0;
    }
    //turn on or off base on boolean
    public void setFlag(int flag, boolean value) {
        if (value) setFlag(flag);
        else clearFlag(flag);
    }
    public boolean isMoving(){
        return hasFlag(EntityState.MOVING);
    }
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
}

