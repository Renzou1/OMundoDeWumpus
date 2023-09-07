package org.example;

public class Player {
    private int x = 0;
    private int y = 0;
    private int health = 100;

    private int battery = 2;

    public void MoveUp(Camp[][] board)
    {
        if( y + 1 < board.length)
        {
            board[x][y].setPlayer(false);
            y++;
            board[x][y].setPlayer(true);
        }  else{
            System.out.println("Wall upward\n");
        }
    }

    public void MoveRight(Camp[][] board)
    {
        if(x + 1 < board.length) {
            board[x][y].setPlayer(false);
            x++;
            board[x][y].setPlayer(true);
        }  else{
            System.out.println("Wall forward\n");
        }
    }

    public void MoveLeft(Camp[][] board)
    {
        if(x - 1 >= 0) {
            board[x][y].setPlayer(false);
            x--;
            board[x][y].setPlayer(true);
        }  else {
            System.out.println("Wall forward\n");
        }
    }

    public void MoveDown(Camp[][] board)
    {
        if(y - 1 >= 0)
        {
            board[x][y].setPlayer(false);
            y--;
            board[x][y].setPlayer(true);
        }  else{
            System.out.println("Wall downard\n");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }
}
