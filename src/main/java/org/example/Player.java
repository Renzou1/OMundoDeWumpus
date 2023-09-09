package org.example;

import static org.example.Main.*;

public class Player {
    private int x = 0;
    private int previousX = 0;
    private int y = 0;
    private int previousY = 0;
    private int health = 100;
    private int wood = 0;
    private int battery = 2;
    private int gold = 0;
    private int bow = 1;
    private int arrows = 0;
    private int weight;

    public void moveUp(Camp[][] board)
    {
        if( y + 1 < board.length)
        {
            if(!board[x][y+1].isPit()) {
                previousX = x;
                previousY = y;
                board[x][y].setPlayer(false);
                y++;
                board[x][y].setPlayer(true);
                board[x][y].setHidden(false);
            }
        }
    }

    public void moveRight(Camp[][] board)
    {
        if(x + 1 < board.length) {
            if(!board[x+1][y].isPit()) {
                previousX = x;
                previousY = y;
                board[x][y].setPlayer(false);
                x++;
                board[x][y].setPlayer(true);
                board[x][y].setHidden(false);
            }
        }
    }

    public void moveLeft(Camp[][] board)
    {
        if(x - 1 >= 0 && !board[x-1][y].isPit()) {
            previousX = x;
            previousY = y;
            board[x][y].setPlayer(false);
            x--;
            board[x][y].setPlayer(true);
            board[x][y].setHidden(false);
        }
    }

    public void moveDown(Camp[][] board)
    {
        if(y - 1 >= 0 && !board[x][y-1].isPit())
        {
            previousX = x;
            previousY = y;
            board[x][y].setPlayer(false);
            y--;
            board[x][y].setPlayer(true);
            board[x][y].setHidden(false);
        }
    }

    public void useLantern(Camp[][] board, int direction)
    {
        int current_x = x;
        int current_y = y;
        if(direction == UP)
        {
            while(current_y < board.length) {
                board[x][current_y++].setHidden(false);
            }
        }
        else if(direction == RIGHT)
        {
            while(current_x < board.length) {
                board[current_x++][y].setHidden(false);
            }
        }
        else if(direction == LEFT)
        {
            while(current_x >= 0) {
                board[current_x--][y].setHidden(false);
            }
        }
        else if(direction == DOWN)
        {
            while(current_y >= 0) {
                board[x][current_y--].setHidden(false);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public boolean isGold() {
        if(gold == 1)
        return true;
        return false;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getWeight() {
        weight = gold + bow + wood + arrows;
        if(battery > 0) weight++; //adds lantern
        return weight;
    }

    public boolean isBow()
    {
        if(bow == 1)
            return true;
        return false;
    }

    public boolean isArrow()
    {
        if(arrows == 1)
            return true;
        return false;
    }
}