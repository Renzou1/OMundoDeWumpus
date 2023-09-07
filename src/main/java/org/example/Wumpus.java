package org.example;

public class Wumpus {

    private int x;

    private int y;

    public void createSmellAndPosition(Camp[][] board)
    {
        board[x][y].setWumpus(true);

        if(x + 1 < board.length) board[x+1][y].setSmelly(true);
        if(y + 1 < board.length) board[x][y+1].setSmelly(true);
        if(x - 1 >= 0) board[x-1][y].setSmelly(true);
        if(y - 1 >= 0) board[x][y-1].setSmelly(true);
    }

    public void clearSmellAndPosition(Camp[][] board)
    {
        board[x][y].setWumpus(false);

        if(x + 1 < board.length) board[x+1][y].setSmelly(false);
        if(y + 1 < board.length) board[x][y+1].setSmelly(false);
        if(x - 1 >= 0) board[x-1][y].setSmelly(false);
        if(y - 1 >= 0) board[x][y-1].setSmelly(false);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
