package org.example;

public class Game {

    private Camp[][] Board;

    public void GenerateBoard()
    {
        Board = new Camp[15][15];


        for(int i = 0; i < 15; i ++)
        {
            for(int j = 0; j < 15; j++)
            {
                Board[i][j] = new Camp();
            }
        }
        Board[0][0].setPlayer(true);

        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {

            }
        }
        System.out.println(Board[0][0].isWindy());
    }
}
