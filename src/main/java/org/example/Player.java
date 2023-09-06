package org.example;

public class Player {
    private int x = 0;
    private int y = 0;

    public void MoveUp(Camp[][] board)
    {
        board[x][y].setPlayer(false);
        y++;
        board[x][y].setPlayer(true);
    }

    public void MoveRight(Camp[][] board)
    {
        board[x][y].setPlayer(false);
        x++;
        board[x][y].setPlayer(true);
    }

    public void MoveLeft(Camp[][] board)
    {
        board[x][y].setPlayer(false);
        x--;
        board[x][y].setPlayer(true);
    }

    public void MoveDown(Camp[][] board)
    {
        board[x][y].setPlayer(false);
        y--;
        board[x][y].setPlayer(true);
    }
}
