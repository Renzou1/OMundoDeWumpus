package org.example;

import java.util.Random;

import static org.example.Main.*;

public class Wumpus {

    private int x;
    private int previousX;
    private int y;
    private int previousY;
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

    public void move(Camp[][] board, Random rand)
    {
        int wumpusDirection = rand.nextInt(4) + 1;
        previousX = x;
        previousY = y;

        clearSmellAndPosition(board);
        if (wumpusDirection == UP && y + 1 < board.length - 1 && !board[x][y + 1].isPit())
        {
            y++;
        } else if (wumpusDirection == RIGHT && x + 1 < board.length - 1 && !board[x + 1][y].isPit())
        {
            x++;
        } else if (wumpusDirection == LEFT && x - 1 >= 0 && !board[x - 1][y].isPit())
        {
            x--;
        } else if (wumpusDirection == DOWN && y - 1 >= 0 && !board[x][y - 1].isPit())
        {
            y--;
        }
        createSmellAndPosition(board);
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

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }
}