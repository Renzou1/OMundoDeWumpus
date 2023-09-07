package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private Camp[][] board;
    private Player player;
    private Wumpus wumpus;
    public boolean over = false;
    private static final int size = 15;
    public void GenerateBoard(Random rand) {
        board = new Camp[size][size];
        player = new Player();
        wumpus = new Wumpus();

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
        wumpus.setX(x);
        wumpus.setY(y);
        wumpus.createSmell(board);

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

    public void monsterTurn(Random rand)
    {
        int wumpusDirection = rand.nextInt(2);

        if (wumpusDirection == 0 && wumpus.getY() - 1 >= 0 && !board[wumpus.getX()][wumpus.getY() - 1].isPit())
        {
            wumpus.clearSmell(board);
            wumpus.setY(wumpus.getY() - 1);
            wumpus.createSmell(board);
        } else if (wumpusDirection == 1 && wumpus.getX() + 1 < board.length && !board[wumpus.getX() + 1][wumpus.getY()].isPit())
        {
            wumpus.clearSmell(board);
            wumpus.setX(wumpus.getX() + 1);
            wumpus.createSmell(board);
        } else if (wumpusDirection == 2 && wumpus.getX() - 1 >= 0 && !board[wumpus.getX() - 1][wumpus.getY()].isPit())
        {
            wumpus.clearSmell(board);
            wumpus.setX(wumpus.getX() - 1);
            wumpus.createSmell(board);
        } else if (wumpusDirection == 3 && wumpus.getY() + 1 < board.length && !board[wumpus.getX()][wumpus.getY() + 1].isPit())
        {
            wumpus.clearSmell(board);
            wumpus.setY(wumpus.getY() + 1);
            wumpus.createSmell(board);
        }
    }


    public void printOptions(Scanner sc)
    {
        if(board[player.getX()][player.getY()].isWumpus())
        {
            player.setHealth(0);
            System.out.println("Voce foi morto\n");
            return;
        }
        if(board[player.getX()][player.getY()].isSmelly())
        {
            System.out.println("You sense the smell of a beast.\n");
        }
        if(board[player.getX()][player.getY()].isWindy())
        {
            System.out.println("There`s a pit nearby.\n");
        }
        if(player.getX() == size)
        {
            System.out.println("Parede acima\n");
        }
        if(player.getX() == 0)
        {
            System.out.println("Parede abaixo.\n");
        }
        if(player.getY() == size)
        {
            System.out.println("Parede a direita.\n");
        }
        if(player.getY() == 0)
        {
            System.out.println("Parede a esquerda.\n");
        }
        if(player.getBattery() > 0) System.out.println("0. Use lantern\n");
        System.out.println("1. Move up\n");
        System.out.println("2. Move right\n");
        System.out.println("3. Move left\n");
        System.out.println("4. Move down\n");
        if(board[player.getX()][player.getY()].isGold())
        {
            System.out.println("You can see something shiny...\n5. Pick the gold up\n");
        }
        if(board[player.getX()][player.getY()].isWood())
        {
            System.out.println("There`s wood where you stand\n6. Pick the wood up\n");
        }
        int choice = sc.nextInt();
        if(choice == 0)
        {
            System.out.println("Escolha a direcao:\n");
            System.out.println("1. up\n");
            System.out.println("2. right\n");
            System.out.println("3. left\n");
            System.out.println("4. down\n");

            choice = sc.nextInt();
        }
        else if(choice == 1)
        {
            player.moveUp(board);
        }
        else if(choice == 2)
        {
            player.moveRight(board);
        }
        else if(choice == 3)
        {
            player.moveLeft(board);
        }
        else if(choice == 4)
        {
            player.moveDown(board);
        }

    }
    public static int getSize() {
        return size;
    }

    public boolean isOver() {
        return over;
    }
}
