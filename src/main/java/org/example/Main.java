package org.example;

import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Game game = new Game();
        game.GenerateBoard(rand);

        while(true)
        {
            game.start();
            game.updateGameState();
        }
    }


}