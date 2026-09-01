package com.pacman.demo.game.behaviour;
import com.pacman.demo.game.map.GameMap;
public interface Moveable {
    public void move(GameMap map);
    public boolean isImmobilized();
    public boolean isMoving();
}
