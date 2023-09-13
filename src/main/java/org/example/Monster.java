package org.example;

abstract public class Monster extends Moveable{
    protected boolean alive = true;

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;

    }
}
