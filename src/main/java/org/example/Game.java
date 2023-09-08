package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import static org.example.Main.*;

public class Game implements ActionListener {
    private int width;
    private int height;
    private Camp[][] board;
    private Player player;
    private Wumpus wumpus;
    private Monster2 monster2;
    private JFrame gameFrame;
    private JLabel[][] campLabels;
    private int size = 15;
    private boolean over = false;
    private JTextArea console;
    private JButton lantern_b;
    private JButton gold_b;
    private JButton wood_b;
    private JButton up_b;
    private JButton right_b;
    private JButton left_b;
    private JButton down_b;
    static final int titleSize = 40;
    private ImageIcon campIcon;
    private ImageIcon playerIcon;
    private Random rand;

    Game(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    public void GenerateBoard(){

        board = new Camp[size][size];
        player = new Player();
        wumpus = new Wumpus();
        monster2 = new Monster2();

        gameFrame = new JFrame();
        gameFrame.setVisible(true);
        gameFrame.setSize(width,height);
        gameFrame.setTitle("O Mundo de Wumpus");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().setLayout(null);
        gameFrame.setResizable(false);
        GridBagConstraints c = new GridBagConstraints();
        campLabels = new JLabel[board.length][board.length];
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(board.length, board.length));
        tilePanel.setVisible(true);
        JPanel playerPanel = new JPanel();
        playerPanel.setVisible(true);
        playerPanel.setLayout(new GridLayout(0,1));


        up_b = new JButton();
        up_b.setVisible(true);
        up_b.addActionListener(this);
        right_b = new JButton();
        right_b.setVisible(true);
        right_b.addActionListener(this);
        left_b = new JButton();
        left_b.setVisible(true);
        left_b.addActionListener(this);
        down_b = new JButton();
        down_b.setVisible(true);
        down_b.addActionListener(this);
        lantern_b = new JButton("Usar lanterna");
        lantern_b.setVisible(true);
        lantern_b.addActionListener(this);
        gold_b = new JButton("Pegar ouro");
        gold_b.setEnabled(false);
        gold_b.setVisible(false);
        gold_b.addActionListener(this);
        wood_b = new JButton("Pegar madeira");
        wood_b.setEnabled(false);
        wood_b.setVisible(false);
        wood_b.addActionListener(this);

        console = new JTextArea();
        console.setVisible(true);
        up_b.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\upArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), (int)((height-titleSize)/7), Image.SCALE_DEFAULT)));
        right_b.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\rightArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), (int)((height-titleSize)/7), Image.SCALE_DEFAULT)));
        left_b.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\leftArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), (int)((height-titleSize)/7), Image.SCALE_DEFAULT)));
        down_b.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\downArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), (int)((height-titleSize)/7), Image.SCALE_DEFAULT)));

        up_b.addActionListener(this);
        right_b.addActionListener(this);
        left_b.addActionListener(this);
        down_b.addActionListener(this);
        lantern_b.addActionListener(this);
        gold_b.addActionListener(this);
        wood_b.addActionListener(this);

        playerPanel.add(up_b);
        playerPanel.add(right_b);
        playerPanel.add(left_b);
        playerPanel.add(down_b);
        playerPanel.add(lantern_b);
        playerPanel.add(gold_b);
        playerPanel.add(wood_b);
        playerPanel.add(console);


        tilePanel.setBounds(0,0, (int)(0.8*width),height-titleSize);
        playerPanel.setBounds(tilePanel.getWidth(), 0,  width-tilePanel.getWidth(), height-titleSize);
        playerIcon = new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\Player.png").getImage().getScaledInstance((int)(tilePanel.getWidth()/board.length), (int)(tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        campIcon = new ImageIcon(new ImageIcon("C:\\Users\\renzo\\IdeaProjects\\OMundoDeWumpus\\resources\\Camp.png").getImage().getScaledInstance((int)(tilePanel.getWidth()/board.length), (int)(tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        for(int i = board.length - 1; i >= 0; i--)
        {
            for(int j = 0; j < board.length; j++)
            {
                campLabels[j][i] = new JLabel();
                campLabels[j][i].setVisible(false);
                campLabels[j][i].setIcon(campIcon);
                tilePanel.add(campLabels[j][i]);
            }
        }

        gameFrame.getContentPane().add(tilePanel);
        gameFrame.getContentPane().add(playerPanel);
        gameFrame.getContentPane().revalidate();
        gameFrame.getContentPane().repaint();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
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
        if(x + 1 < board.length) board[x+1][y].setWindy(true);
        if(y + 1 < board.length) board[x][y+1].setWindy(true);
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

    public void monsterTurn()
    {
        //wumpus movement
        int wumpusDirection = rand.nextInt(4) + 1;

        wumpus.clearSmellAndPosition(board);
        if (wumpusDirection == UP && wumpus.getY() + 1 < board.length && !board[wumpus.getX()][wumpus.getY() + 1].isPit())
        {
            wumpus.setY(wumpus.getY() + 1);
        } else if (wumpusDirection == RIGHT && wumpus.getX() + 1 < board.length && !board[wumpus.getX() + 1][wumpus.getY()].isPit())
        {
            wumpus.setX(wumpus.getX() + 1);
        } else if (wumpusDirection == LEFT && wumpus.getX() - 1 >= 0 && !board[wumpus.getX() - 1][wumpus.getY()].isPit())
        {
            wumpus.setX(wumpus.getX() - 1);
        } else if (wumpusDirection == DOWN && wumpus.getY() - 1 >= 0 && !board[wumpus.getX()][wumpus.getY() - 1].isPit())
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
        }  else if (monster2Direction1 == DOWN && monster2.getY() - 2 < board.length)
        {
            monster2.setY(monster2.getY() - 1);
            monster2.checkPlayerContact(player);
            monster2.setY(monster2.getY() - 1);
            if(monster2Direction2 == 0 && monster2.getX() - 1 >= 0)  //left
            {
                monster2.setX(monster2.getX() - 1);
            }  else{
                monster2.setX(monster2.getX() + 1);
            }
        }
        monster2.checkPlayerContact(player); // after second movement
        playerTurn();
    }


    public void playerTurn() {

        if (board[player.getX()][player.getY()].isWumpus() || player.getHealth() <= 0) {
            player.setHealth(0);
            console.setText("Voce foi morto. Fim de Jogo\n");
            over = true;
            return;
        } else if (player.getX() == 0 && player.getY() == 0 && player.isGold()) {
            console.setText("Voce ganhou! Parabens.\n");
            over = true;
            return;
        }

        campLabels[player.getX()][player.getY()].setIcon(playerIcon);

        if(player.getY() + 1 < board.length) campLabels[player.getX()][player.getY()+1].setIcon(campIcon);
        if(player.getX() + 1 < board.length) campLabels[player.getX()+1][player.getY()].setIcon(campIcon);
        if(player.getX() - 1 >= 0) campLabels[player.getX()-1][player.getY()].setIcon(campIcon);
        if(player.getY() - 1 >= 0) campLabels[player.getX()][player.getY()-1].setIcon(campIcon);

        console.setText("");
        console.append("Vida: " + String.valueOf(player.getHealth()) + "\n");
        if(player.getWood() > 0) console.append("Madeiras em inventario: " + player.getWood());
        if (board[player.getX()][player.getY()].isSmelly()) {
            console.append("Voce sente o cheiro do wumpus\n");
        }
        if (board[player.getX()][player.getY()].isWindy()) {
            console.append("Voce sente o vento de um abismo\n");
        }
        if (player.getX() == board.length - 1) {
            console.append("Parede a direita\n");
        }
        if (player.getY() == 0) {
            console.append("Parede abaixo.\n");
        }
        if (player.getY() == board.length - 1) {
            console.append("Parede acima.\n");
        }
        if (player.getX() == 0) {
            console.append("Parede a esquerda.\n");
        }


        if (player.getBattery() <= 0 && lantern_b.isVisible()) {
            lantern_b.setVisible(false);
            lantern_b.setEnabled(false);
        }
        if (board[player.getX()][player.getY()].isGold()) {
            console.append("Voce consegue ver algo brilhante...\n");
            gold_b.setVisible(true);
            gold_b.setEnabled(true);
        }
        if (board[player.getX()][player.getY()].isWood()) {
            console.append("Voce sente uma madeira aos seus pes\n");
            wood_b.setVisible(true);
            wood_b.setEnabled(true);
        }

        if(!board[player.getX()][player.getY()].isGold())
        {
            gold_b.setVisible(false);
            gold_b.setEnabled(false);
        }
        if(!board[player.getX()][player.getY()].isWood())
        {
            wood_b.setVisible(false);
            wood_b.setEnabled(false);
        }
        campLabels[player.getX()][player.getY()].setVisible(true);
        if (board[player.getX()][player.getY()].isWumpus()) {
            player.setHealth(0);
            console.setText("Voce foi morto. Fim de Jogo.");
            over = true;
        } else if (player.getX() == 0 && player.getY() == 0 && player.isGold()) {
            console.setText("Voce ganhou. Parabens!\n");
            over = true;
        }
    }

    public boolean isOver() {
        return over;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lantern_b) {
            //choice = LANTERN;
            console.setText("Escolha a direcao");
        } else if (e.getSource() == up_b) {
            player.moveUp(board);
        } else if (e.getSource() == right_b) {
            player.moveRight(board);
        } else if (e.getSource() == left_b) {
            player.moveLeft(board);
        } else if (e.getSource() == down_b) {
            player.moveDown(board);
        } else if (e.getSource() == gold_b) {
            board[player.getX()][player.getY()].setGold(false);
            player.setGold(true);
            console.append("Ouro em inventario.\n");
            gold_b.setVisible(false);
            gold_b.setEnabled(false);
        } else if (e.getSource() == wood_b) {
            board[player.getX()][player.getY()].setWood(false);
            player.setWood(player.getWood() + 1);
            console.append("Madeiras em inventario: " + player.getWood());
            wood_b.setVisible(false);
            wood_b.setEnabled(false);
        }
        monsterTurn();
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}

