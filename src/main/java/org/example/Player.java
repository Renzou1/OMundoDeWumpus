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
    private boolean gold = false;

    public void moveUp(Camp[][] board)
    {
        previousY = y;
        if( y + 1 < board.length)
        {
            board[x][y].setPlayer(false);
            y++;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede");
            health--;
        }
    }

    public void moveRight(Camp[][] board)
    {
        previousX = x;
        if(x + 1 < board.length) {
            board[x][y].setPlayer(false);
            x++;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede");
            health--;
        }
    }

    public void moveLeft(Camp[][] board)
    {
        previousX = x;
        if(x - 1 >= 0) {
            board[x][y].setPlayer(false);
            x--;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else {
            System.out.println("Voce da de cara com a parede");
            health--;
        }
    }

    public void moveDown(Camp[][] board)
    {
        previousY = y;
        if(y - 1 >= 0)
        {
            board[x][y].setPlayer(false);
            y--;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede");
            health--;
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
            while(current_x > 0) {
                board[current_x--][y].setHidden(false);
            }
        }
        else if(direction == DOWN)
        {
            while(current_y < board.length) {
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

    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
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
}
