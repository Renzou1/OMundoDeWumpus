package org.example;

import java.util.Random;

import static org.example.Main.*;

public class Monster2 {

    private int x;

    private int previousX;
    private int y;

    private int previousY;
    public void checkPlayerContact(Player player)
    {
        if(player.getX() == x && player.getY() == y)
        {
            player.setHealth(player.getHealth() - 50);
        }
    }

    public void move(Camp[][] board, Random rand, Player player)
    {
        //monster2 movement
        int monster2Direction1 = rand.nextInt(4) + 1;
        int monster2Direction2 = rand.nextInt(2);
        checkPlayerContact(player); // if player moved to monster

        previousX = x;
        previousY = y;

        if(monster2Direction1 == UP && y + 2 < board.length - 1)
        {
            y++;
            checkPlayerContact(player);
            y++;
            checkPlayerContact(player);

            if(x + 1 >= board.length)  //left
            {
                x--;
            }  else if (x - 1 <= 0){
                x++;
            }  else {
                if(monster2Direction2 == 0) x--;
                else x++;
            }
        }  else if (monster2Direction1 == RIGHT && x + 2 < board.length - 1)
        {
            x++;
            checkPlayerContact(player);
            x++;
            checkPlayerContact(player);

            if(y + 1 >= board.length)  //down
            {
                y--;
            }  else if (y - 1 <= 0){
                y++;
            }  else{
                if(monster2Direction2 == 0) y--;
                else y++;
            }
        }  else if (monster2Direction1 == LEFT && x - 2 >= 0)
        {
            x--;
            checkPlayerContact(player);
            x--;
            checkPlayerContact(player);

            if(y + 1 >= board.length)  //down
            {
                y--;
            }  else if (y - 1 <= 0){
                y++;
            }  else{
                if(monster2Direction2 == 0) y--;
                else y++;
            }
        }  else if (monster2Direction1 == DOWN && y - 2 >= 0)
        {
            y--;
            checkPlayerContact(player);
            y--;
            if(x + 1 >= board.length)  //left
            {
                x--;
            }  else if (x - 1 <= 0){
                x++;
            }  else {
                if(monster2Direction2 == 0) x--;
                else x++;
            }
        }
        checkPlayerContact(player); // after second movement
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

    public void setY(int y){
        this.y = y;
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }
}
