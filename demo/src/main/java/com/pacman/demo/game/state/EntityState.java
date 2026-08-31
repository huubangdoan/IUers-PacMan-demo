package com.pacman.demo.game.state;

public class EntityState {
    //General
    public static final int MOVING       = 1 << 0;
    public static final int WAS_STUCK    = 1 << 1;
    public static final int WALL_HACK    = 1 << 2;
    //PacMan
    public static final int HAS_THORNS     = 1 << 3;
    public static final int HAS_POWERUP    = 1 << 4;
    public static final int HAS_WATERMELON = 1 << 5;
    public static final int HAS_CHILLI     = 1 << 6;
    public static final int DRAGON_MODE    = 1 << 7;
    public static final int IS_DISGUISED   = 1 << 8;
    //Ghost
    public static final int FRIGHTENED = 1 << 9; 
    public static final int EATEN      = 1 << 10;
    public static final int IN_HOUSE   = 1 << 11;
    public static final int STUNNED    = 1 << 12;
    public static final int FROZEN     = 1 << 13;
}
