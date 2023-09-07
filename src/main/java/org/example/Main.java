package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int UP = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;
    static final int DOWN = 4;
    public static void main(String[] args) {
        Random rand = new Random();
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        game.GenerateBoard(rand);

        System.out.println("Welcome to wumpus\n");

        while(!game.isOver())
        {
            game.playerTurn(sc);
            game.monsterTurn(rand);
        }


    }


}