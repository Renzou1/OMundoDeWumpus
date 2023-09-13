package org.example;

abstract public class Moveable {
    protected int x = 0;
    protected int previousX = 0;
    protected int y = 0;
    protected int previousY = 0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y){
        this.y = y;
    }
    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }
}
