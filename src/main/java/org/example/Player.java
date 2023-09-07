package org.example;

public class Player {
    private int x = 0;
    private int y = 0;
    private int health = 100;
    private int battery = 2;

    public void moveUp(Camp[][] board)
    {
        if( y + 1 < board.length)
        {
            board[x][y].setPlayer(false);
            y++;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede\n");
            health--;
        }
    }

    public void moveRight(Camp[][] board)
    {
        if(x + 1 < board.length) {
            board[x][y].setPlayer(false);
            x++;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede\n");
            health--;
        }
    }

    public void moveLeft(Camp[][] board)
    {
        if(x - 1 >= 0) {
            board[x][y].setPlayer(false);
            x--;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else {
            System.out.println("Voce da de cara com a parede\n");
            health--;
        }
    }

    public void moveDown(Camp[][] board)
    {
        if(y - 1 >= 0)
        {
            board[x][y].setPlayer(false);
            y--;
            board[x][y].setPlayer(true);
            board[x][x].setHidden(false);
        }  else{
            System.out.println("Voce da de cara com a parede\n");
            health--;
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
