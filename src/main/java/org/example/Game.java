package org.example;

import java.util.Random;
import java.util.Scanner;

import static org.example.Main.*;

public class Game {
    private Camp[][] board;
    private Player player;
    private Wumpus wumpus;
    private Monster2 monster2;
    private boolean over = false;
    private static final int size = 15;
    public void GenerateBoard(Random rand) {
        board = new Camp[size][size];
        player = new Player();
        wumpus = new Wumpus();
        monster2 = new Monster2();

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
        } while (x == 0 && y == 0);

        board[x][y].setPit(true);
        if(x + 1 < size) board[x+1][y].setWindy(true);
        if(y + 1 < size) board[x][y+1].setWindy(true);
        if(x - 1 >= 0) board[x-1][y].setWindy(true);
        if(y - 1 >= 0) board[x][y-1].setWindy(true);

        //creates Wumpus
        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x == 0 && y == 0  && !board[x][y].isPit());

        board[x][y].setWumpus(true);
        wumpus.setX(x);
        wumpus.setY(y);
        wumpus.createSmellAndPosition(board);

        //creates Monster2
        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x == 0 && y == 0  && !board[x][y].isPit());

        board[x][y].setMonster2(true);
        monster2.setX(x);
        monster2.setY(y);

        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x == 0 && y == 0  && !board[x][y].isPit());

        board[x][y].setGold(true);

        do {
            x = rand.nextInt(15);
            y = rand.nextInt(15);
        } while (x == 0 && y == 0  && !board[x][y].isPit());

        board[x][y].setWood(true);
    }

    public void monsterTurn(Random rand)
    {
        if(over == true) return;

        //wumpus movement
        int wumpusDirection = rand.nextInt(4) + 1;

        wumpus.clearSmellAndPosition(board);
        if (wumpusDirection == UP && wumpus.getY() - 1 >= 0 && !board[wumpus.getX()][wumpus.getY() - 1].isPit())
        {
            wumpus.setY(wumpus.getY() + 1);
        } else if (wumpusDirection == RIGHT && wumpus.getX() + 1 < board.length && !board[wumpus.getX() + 1][wumpus.getY()].isPit())
        {
            wumpus.setX(wumpus.getX() + 1);
        } else if (wumpusDirection == LEFT && wumpus.getX() - 1 >= 0 && !board[wumpus.getX() - 1][wumpus.getY()].isPit())
        {
            wumpus.setX(wumpus.getX() - 1);
        } else if (wumpusDirection == DOWN && wumpus.getY() + 1 < board.length && !board[wumpus.getX()][wumpus.getY() + 1].isPit())
        {
            wumpus.setY(wumpus.getY() - 1);
        }
        wumpus.createSmellAndPosition(board);

        //monster2 movement
        int monster2Direction1 = rand.nextInt(4) + 1;
        int monster2Direction2 = rand.nextInt(2);
        monster2.checkPlayerContact(player); // if player moved to monster

        if(monster2Direction1 == UP && monster2.getY() + 2 < board.length)
        {
            monster2.setY(monster2.getY() + 1);
            monster2.checkPlayerContact(player);
            monster2.setY(monster2.getY() + 1);
            monster2.checkPlayerContact(player);

            if(monster2Direction2 == 0 && monster2.getX() - 1 >= 0) //left
            {
                monster2.setX(monster2.getX() - 1);
            }  else{
                monster2.setX(monster2.getX() + 1);
            }
            monster2.checkPlayerContact(player);
        }  else if (monster2Direction1 == RIGHT && monster2.getX() + 2 < board.length)
        {
            monster2.setX(monster2.getX() + 1);
            monster2.checkPlayerContact(player);
            monster2.setX(monster2.getX() + 1);
            monster2.checkPlayerContact(player);

            if(monster2Direction2 == 0 && monster2.getY() - 1 >= 0) //down
            {
                monster2.setY(monster2.getY() - 1);
            }  else {
                monster2.setY(monster2.getY() + 1);
            }
            monster2.checkPlayerContact(player);

        }  else if (monster2Direction1 == LEFT && monster2.getX() - 2 < board.length)
        {
            monster2.setX(monster2.getX() - 1);
            monster2.checkPlayerContact(player);
            monster2.setX(monster2.getX() - 1);
            monster2.checkPlayerContact(player);

            if(monster2Direction2 == 0 && monster2.getY() - 1 >= 0)  //down
            {
                monster2.setY(monster2.getY() - 1);
            }  else{
                monster2.setY(monster2.getY() + 1);
            }
            monster2.checkPlayerContact(player);

        }  else if (monster2Direction1 == DOWN && monster2.getY() - 2 < board.length)
        {
            monster2.setY(monster2.getY() - 1);
            monster2.checkPlayerContact(player);
            monster2.setY(monster2.getY() - 1);
            monster2.checkPlayerContact(player);

            if(monster2Direction2 == 0 && monster2.getX() - 1 >= 0)  //left
            {
                monster2.setX(monster2.getX() - 1);
            }  else{
                monster2.setX(monster2.getX() + 1);
            }
            monster2.checkPlayerContact(player);

        }
    }


    public void playerTurn(Scanner sc)
    {
        if(board[player.getX()][player.getY()].isWumpus() || player.getHealth() <= 0)
        {
            player.setHealth(0);
            System.out.println("Voce foi morto");
            over = true;
            return;
        }  else if(player.getX() == 0 && player.getY() == 0 && player.isGold())
        {
            System.out.println("Voce ganhou");
            over = true;
        }
        if(board[player.getX()][player.getY()].isSmelly())
        {
            System.out.println("You sense the smell of a beast.");
        }
        if(board[player.getX()][player.getY()].isWindy())
        {
            System.out.println("There`s a pit nearby.");
        }
        if(player.getX() == size - 1)
        {
            System.out.println("Parede a direita");
        }
        if(player.getY() == 0)
        {
            System.out.println("Parede abaixo.");
        }
        if(player.getY() == size - 1)
        {
            System.out.println("Parede acima.");
        }
        if(player.getX() == 0)
        {
            System.out.println("Parede a esquerda.");
        }
        if(player.getBattery() > 0) System.out.println("0. Use lantern");
        System.out.println("1. Move up");
        System.out.println("2. Move right");
        System.out.println("3. Move left");
        System.out.println("4. Move down");
        if(board[player.getX()][player.getY()].isGold())
        {
            System.out.println("You can see something shiny...\n5. Pick the gold up");
        }
        if(board[player.getX()][player.getY()].isWood())
        {
            System.out.println("There`s wood where you stand\n6. Pick the wood up");
        }
        int choice = sc.nextInt();
        if(choice == 0)
        {
            System.out.println("Escolha a direcao:");
            System.out.println("1. up");
            System.out.println("2. right");
            System.out.println("3. left");
            System.out.println("4. down");

            choice = sc.nextInt();
            player.useLantern(board, choice);
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
        else if(choice == 5 && board[player.getX()][player.getY()].isGold())
        {
            board[player.getX()][player.getY()].setGold(false);
            player.setGold(true);
        }
        if(board[player.getX()][player.getY()].isWumpus())
        {
            player.setHealth(0);
            System.out.println("Voce foi morto");
            over = true;
            return;
        }  else if(player.getX() == 0 && player.getY() == 0 && player.isGold())
        {
            System.out.println("Voce ganhou");
            over = true;
        }


    }
    public static int getSize() {
        return size;
    }

    public boolean isOver() {
        return over;
    }
}
