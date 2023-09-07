package org.example;

import java.util.Random;

public class Game {
    private Camp[][] board;
    private Player player;

    private static final int size = 15;
    public void GenerateBoard(Random rand) {
        board = new Camp[size][size];
        player = new Player();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Camp();
            }
        }
        board[0][0].setPlayer(true);
        board[0][0].setHidden(false);

        int x;
        int y;

        //creates Pit
        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x != 0 || y != 0);

        board[x][y].setPit(true);
        if(x + 1 < size) board[x+1][y].setWindy(true);
        if(y + 1 < size) board[x][y+1].setWindy(true);
        if(x - 1 >= 0) board[x-1][y].setWindy(true);
        if(y - 1 >= 0) board[x][y-1].setWindy(true);

        //creates Wumpus
        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x != 0 || y != 0  && board[x][y].isPit() == false);

        board[x][y].setWumpus(true);
        if(x + 1 < size) board[x+1][y].setSmelly(true);
        if(y + 1 < size) board[x][y+1].setSmelly(true);
        if(x - 1 >= 0) board[x-1][y].setSmelly(true);
        if(y - 1 >= 0) board[x][y-1].setSmelly(true);

        //creates Monster2
        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x != 0 || y != 0  && board[x][y].isPit() == false);

        board[x][y].setMonster2(true);

        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x != 0 || y != 0  && board[x][y].isPit() == false);

        board[x][y].setGold(true);

        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x != 0 || y != 0  && board[x][y].isPit() == false);

        board[x][y].setWood(true);
    }

    public void updateGameState()
    {
        for(int x = 0; x < size; x++)
        {
            for(int y = 0; y < size; y++)
            {
                if(board[x][y].isPlayer() && board[x][y].isHidden())
                {
                    board[x][y].setHidden(false);
                }
                if(board[x][y].isPlayer() && board[x][y].isWumpus())
                {
                    player.setHealth(0);
                    //game over
                }
                if(board[x][y].isPlayer() && board[x][y].isGold())
                {
                    System.out.println("you feel gold");
                }
                if(board[x][y].isPlayer() && board[x][y].isWood())
                {
                    System.out.println("Ha madeira");
                }
            }
        }
    }

    public void start()
    {
        System.out.println("Welcome to wimups\n");
    }

    public static int getSize() {
        return size;
    }
}
