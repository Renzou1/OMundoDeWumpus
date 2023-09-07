package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Game game = new Game();
        Scanner sc = new Scanner(System.in);
        game.GenerateBoard(rand);

        System.out.println("Welcome to wumpus\n");

        while(!game.isOver())
        {
            game.monsterTurn(rand);
            game.printOptions(sc);
        }
    }


}