package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static org.example.Main.*;

public class Game implements ActionListener{
    private final int width;
    private final int height;
    private Camp[][] board;
    private Player player;
    private Wumpus wumpus;
    private Monster2 monster2;
    private JLabel[][] campLabels;
    private final int size;
    private JTextArea console;
    private  JButton drop_b;
    private JButton arrow_b;
    private  JButton shoot_b;
    private JButton lantern_b;
    private JButton gold_b;
    private JButton wood_b;
    private JButton up_b;
    private JButton right_b;
    private JButton left_b;
    private JButton down_b;
    private JButton fill_b;
    static final int titleSize = 40;
    private ImageIcon campIcon;
    private ImageIcon playerIcon;
    private ImageIcon wumpusIcon;
    private ImageIcon monster2Icon;
    private boolean lantern_state = false;
    private boolean shoot_state = false;
    private boolean fill_state = false;
    private boolean normal_state = true;
    private JFrame gameFrame;
    private Random rand;

    Game(int width, int height, int size)
    {
        this.width = width;
        this.height = height;
        this.size = size;
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
        campLabels = new JLabel[board.length][board.length];
        JPanel tilePanel = new JPanel();
        tilePanel.setLayout(new GridLayout(board.length, board.length));
        tilePanel.setVisible(true);
        JPanel playerPanel = new JPanel();
        playerPanel.setVisible(true);
        playerPanel.setLayout(new GridLayout(0,1));


        up_b = new JButton();
        right_b = new JButton();
        left_b = new JButton();
        down_b = new JButton();
        drop_b = new JButton("Largar item");
        lantern_b = new JButton("Usar lanterna");
        gold_b = new JButton("Pegar ouro");
        wood_b = new JButton("Pegar madeira");
        arrow_b = new JButton("Fazer flecha");
        shoot_b = new JButton("Atirar flecha");
        fill_b = new JButton("Tapar abismo");

        up_b.setVisible(true);
        right_b.setVisible(true);
        left_b.setVisible(true);
        down_b.setVisible(true);
        drop_b.setVisible(true);
        lantern_b.setVisible(true);
        gold_b.setVisible(false);
        wood_b.setVisible(false);
        arrow_b.setVisible(false);
        shoot_b.setVisible(false);
        fill_b.setVisible(false);

        gold_b.setEnabled(false);
        wood_b.setEnabled(false);
        arrow_b.setEnabled(false);
        shoot_b.setEnabled(false);
        fill_b.setEnabled(false);

        console = new JTextArea();
        console.setEditable(false);
        console.setVisible(true);
        up_b.setIcon(new ImageIcon(new ImageIcon("resources\\upArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width),((height-titleSize)/13), Image.SCALE_DEFAULT)));
        right_b.setIcon(new ImageIcon(new ImageIcon("resources\\rightArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), ((height-titleSize)/13), Image.SCALE_DEFAULT)));
        left_b.setIcon(new ImageIcon(new ImageIcon("resources\\leftArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), ((height-titleSize)/13), Image.SCALE_DEFAULT)));
        down_b.setIcon(new ImageIcon(new ImageIcon("resources\\downArrow-nobg.png").getImage().getScaledInstance((int)(0.1*width), ((height-titleSize)/13), Image.SCALE_DEFAULT)));

        up_b.addActionListener(this);
        right_b.addActionListener(this);
        left_b.addActionListener(this);
        down_b.addActionListener(this);
        drop_b.addActionListener(this);
        lantern_b.addActionListener(this);
        shoot_b.addActionListener(this);
        arrow_b.addActionListener(this);
        wood_b.addActionListener(this);
        gold_b.addActionListener(this);
        fill_b.addActionListener(this);

        up_b.setFocusable(false);
        right_b.setFocusable(false);
        left_b.setFocusable(false);
        down_b.setFocusable(false);
        drop_b.setFocusable(false);
        lantern_b.setFocusable(false);
        shoot_b.setFocusable(false);
        arrow_b.setFocusable(false);
        wood_b.setFocusable(false);
        gold_b.setFocusable(false);
        fill_b.setFocusable(false);

        console.setFocusable(false);

        playerPanel.add(up_b);
        playerPanel.add(right_b);
        playerPanel.add(left_b);
        playerPanel.add(down_b);
        playerPanel.add(drop_b);
        playerPanel.add(lantern_b);
        playerPanel.add(shoot_b);
        playerPanel.add(arrow_b);
        playerPanel.add(wood_b);
        playerPanel.add(gold_b);
        playerPanel.add(fill_b);

        playerPanel.add(console);

        tilePanel.setBounds(0,0, (int)(0.8*width),height-titleSize);
        playerPanel.setBounds(tilePanel.getWidth(), 0,  width-tilePanel.getWidth(), height-titleSize);
        playerIcon = new ImageIcon(new ImageIcon("resources\\Player.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        campIcon = new ImageIcon(new ImageIcon("resources\\Camp.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        wumpusIcon = new ImageIcon(new ImageIcon("resources\\Wumpus.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        monster2Icon = new ImageIcon(new ImageIcon("resources\\Monster2.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        ImageIcon[] hiddenIcon = new ImageIcon[4];
        hiddenIcon[0] = new ImageIcon(new ImageIcon("resources\\Hidden1.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        hiddenIcon[1] = new ImageIcon(new ImageIcon("resources\\Hidden2.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        hiddenIcon[2] = new ImageIcon(new ImageIcon("resources\\Hidden3.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));
        hiddenIcon[3] = new ImageIcon(new ImageIcon("resources\\Hidden4.png").getImage().getScaledInstance((tilePanel.getWidth()/board.length), (tilePanel.getHeight()/board.length), Image.SCALE_DEFAULT));


        int hiddenType;
        for(int i = board.length - 1; i >= 0; i--)
        {
            for(int j = 0; j < board.length; j++)
            {
                hiddenType = rand.nextInt(4);
                campLabels[j][i] = new JLabel();
                campLabels[j][i].setIcon(hiddenIcon[hiddenType]);
                campLabels[j][i].setVisible(true);
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

        boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
        if(isDebug)
            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board.length; j++)
                    board[i][j].setHidden(false);
        int x;
        int y;

        int boardCamps = board.length * board.length;
        int desiredAmount = boardCamps*10/100;
        int current = 0;
        //creates Pit
        do {
            x = rand.nextInt(board.length);
            y = rand.nextInt(board.length);
            current++;
            board[x][y].setPit(true);
            if(x + 1 < board.length) board[x+1][y].setWindy(true);
            if(y + 1 < board.length) board[x][y+1].setWindy(true);
            if(x - 1 >= 0) board[x-1][y].setWindy(true);
            if(y - 1 >= 0) board[x][y-1].setWindy(true);
        } while (current < desiredAmount || (x == 0 && y == 0));

        //creates Wumpus
        do {
            x = rand.nextInt(board.length);
            y = rand.nextInt(board.length);
        } while ((x == 0 && y == 0)  || board[x][y].isPit());

        board[x][y].setWumpus(true);
        wumpus.setX(x);
        wumpus.setY(y);
        wumpus.createSmellAndPosition(board);

        //creates Monster2
        do {
            x = rand.nextInt(board.length);
            y = rand.nextInt(board.length);
        } while ((x == 0 && y == 0)  || board[x][y].isPit());

        board[x][y].setMonster2(true);
        monster2.setX(x);
        monster2.setY(y);

        do {
            x = rand.nextInt(board.length);
            y = rand.nextInt(board.length);
        } while ((x == 0 && y == 0)  || board[x][y].isPit());

        board[x][y].setGold(true);

        desiredAmount = boardCamps*5/100;
        current = 0;
        do {
            x = rand.nextInt(board.length);
            y = rand.nextInt(board.length);
            current++;
            board[x][y].setWood(true);
        } while (current < desiredAmount || (x == 0 && y == 0)  || board[x][y].isPit());

        this.updateBoard();
        if(isDebug) updateHidden();
    }
    public void monsterTurn()
    {
        //wumpus movement
        if(wumpus.isAlive()) wumpus.move(board, rand);
        if(monster2.isAlive()) monster2.move(board,rand,player);
        playerTurn();
    }

    public void updateBoard()
    {
        //updates player stuff
        campLabels[player.getPreviousX()][player.getPreviousY()].setIcon(campIcon);
        campLabels[player.getX()][player.getY()].setIcon(playerIcon);

        //updates Wumpus
        if(wumpus.isAlive()) {
            int previousX = wumpus.getPreviousX();
            int previousY = wumpus.getPreviousY();
            int x = wumpus.getX();
            int y = wumpus.getY();
            if (!board[previousX][previousY].isPlayer())
                if (!board[previousX][previousY].isHidden())
                    campLabels[previousX][previousY].setIcon(campIcon);
            if (!board[x][y].isHidden()) campLabels[x][y].setIcon(wumpusIcon);
        }
        //updates Monster2
        if(monster2.isAlive()) {
            int previousX = monster2.getPreviousX();
            int previousY = monster2.getPreviousY();
            int x = monster2.getX();
            int y = monster2.getY();
            if (!board[previousX][previousY].isPlayer() && !board[previousX][previousY].isWumpus())
                if (!board[previousX][previousY].isHidden())
                    campLabels[previousX][previousY].setIcon(campIcon);
            if (!board[x][y].isHidden()) campLabels[x][y].setIcon(monster2Icon);
        }

    }

    public void updateHidden()
    {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++)
                if(!board[i][j].isHidden()){
                    if(!board[i][j].isPit()) campLabels[i][j].setIcon(campIcon);
                    //else TODO add pit icon
                }
        updateBoard();
    }
    public void playerTurn() {
        checkIfOver();
        updateConsoleAndButtons();
    }


    public void updateConsoleAndButtons()
    {
        boolean first = true;
        if(normal_state) {
            console.setText("");
            console.append("Vida: " + player.getHealth() + "\n");
            if (player.getWeight() > 0) {
                console.append("Carregando: ");
                if (player.getBattery() > 0) {
                    console.append("lanterna");
                    first = false;
                }
                if (player.isBow()) {
                    if (!first) console.append("/ ");
                    console.append("arco");
                    first = false;
                }
                if (player.getArrows() > 0) {
                    if (!first) console.append("/ ");
                    console.append(player.getArrows() + "flecha(s)");
                    first = false;
                }
                if (player.getWood() > 0) {
                    if (!first) console.append("/ ");
                    console.append(player.getWood() + " madeira(s)");
                    first = false;
                }
                if (player.isGold()) {
                    if (!first) console.append("/ ");
                    console.append("ouro");
                }
                console.append("\n");
            }
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

            if (board[player.getX()][player.getY()].isGold()) {
                console.append("Voce consegue ver algo brilhante...\n");
                gold_b.setVisible(true);
                gold_b.setEnabled(true);
            } else {
                gold_b.setVisible(false);
                gold_b.setEnabled(false);
            }
            if (board[player.getX()][player.getY()].isWood()) {
                console.append("Voce pisa em uma madeira\n");
                wood_b.setVisible(true);
                wood_b.setEnabled(true);
                arrow_b.setVisible(true);
                arrow_b.setEnabled(true);
            } else {
                wood_b.setVisible(false);
                wood_b.setEnabled(false);
                arrow_b.setVisible(false);
                arrow_b.setEnabled(false);
            }
            if (!drop_b.isVisible()) {
                drop_b.setVisible(true);
                drop_b.setEnabled(true);
            }
            if (player.getBattery() > 0) {
                lantern_b.setVisible(true);
                lantern_b.setEnabled(true);
            } else {
                lantern_b.setVisible(false);
                lantern_b.setEnabled(false);
            }

            if (player.getArrows() > 0) {
                shoot_b.setVisible(true);
                shoot_b.setEnabled(true);
            } else {
                shoot_b.setVisible(false);
                shoot_b.setEnabled(false);
            }
            if (board[player.getX()][player.getY()].isWindy() && player.getWood() > 0) {
                fill_b.setVisible(true);
                fill_b.setEnabled(true);
            } else {
                fill_b.setVisible(false);
                fill_b.setEnabled(false);
            }
        }  else{
            console.setText("Escolha a direcao");
        }
        campLabels[player.getX()][player.getY()].setVisible(true);
    }
    public void checkIfOver()
    {
        if (board[player.getX()][player.getY()].isWumpus() || player.getHealth() <= 0) {
            updateBoard();
            player.setHealth(0);
            console.setText("Vida: 0\nVoce foi morto");
            int reply = JOptionPane.showConfirmDialog(null, "Voce foi morto. Jogar de novo?", "Fim de jogo", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                gameFrame.dispose();
                startGame();
            } else {
                System.exit(0);
            }
        } else if (player.getX() == 0 && player.getY() == 0 && player.isGold()) {
            updateBoard();
            int reply = JOptionPane.showConfirmDialog(null, "Voce ganhou! Jogar de novo?", "Fim de jogo", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                gameFrame.dispose();
                startGame();
            } else {
                System.exit(0);
            }
            console.setText("Voce ganhou! Parabens.\n");
        }
    }

    public void hideNonDirectionButtons()
    {
        lantern_b.setVisible(false);
        gold_b.setVisible(false);
        wood_b.setVisible(false);
        arrow_b.setVisible(false);
        shoot_b.setVisible(false);
        drop_b.setVisible(false);

        gold_b.setEnabled(false);
        wood_b.setEnabled(false);
        arrow_b.setEnabled(false);
        shoot_b.setEnabled(false);
        drop_b.setEnabled(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == lantern_b) {
            player.setBattery(player.getBattery() - 1);
            lantern_state = true;
            if (player.getBattery() == 0) {
                lantern_b.setVisible(false);
                lantern_b.setEnabled(false);
            }

        } else if (source == up_b) {
            if(normal_state)
            {
                player.moveUp(board);
            }  else if(lantern_state){
                player.useLantern(board, UP);
                updateHidden();
                lantern_state = false;
            }  else if (shoot_state){
                player.setArrows(player.getArrows() - 1);
               if(player.getY() + 1 < board.length && board[player.getX()][player.getY() + 1].isWumpus())
                   wumpus.setAlive(false);
                if(player.getY() + 1 < board.length && board[player.getX()][player.getY() + 1].isMonster2())
                    monster2.setAlive(false);
                shoot_state = false;
            }  else if (fill_state){
                player.setWood(player.getWood() - 1);
                if(player.getY() + 1 < board.length && board[player.getX()][player.getY() + 1].isPit()) {
                    board[player.getX()][player.getY() + 1].setPit(false);
                    campLabels[player.getX()][player.getY() + 1].setIcon(campIcon); //todo create another icon
                }
                fill_state = false;
            }

        } else if (source == right_b) {
            if(normal_state)
            {
                player.moveRight(board);
            } else if(lantern_state){
                player.useLantern(board, RIGHT);
                updateHidden();
                lantern_state = false;
            }  else if (shoot_state){
                player.setArrows(player.getArrows() - 1);
               if(player.getX() + 1 < board.length && board[player.getX() + 1][player.getY()].isWumpus())
                   wumpus.setAlive(false);
                if(player.getX() + 1 < board.length && board[player.getX() + 1][player.getY()].isMonster2())
                    monster2.setAlive(false);
                shoot_state = false;
            }  else if (fill_state){
                player.setWood(player.getWood() - 1);
                if(player.getX() + 1 < board.length && board[player.getX() + 1][player.getY()].isPit()) {
                    board[player.getX() + 1][player.getY()].setPit(false);
                    campLabels[player.getX() + 1][player.getY()].setIcon(campIcon); //todo create another icon
                }
                fill_state = false;
            }

        } else if (source == left_b) {
            if(normal_state){
                player.moveLeft(board);
            }  else if(lantern_state){
                player.useLantern(board, LEFT);
                updateHidden();
                lantern_state = false;
            }  else if (shoot_state){
                player.setArrows(player.getArrows() - 1);
               if(player.getX() - 1 >= 0 && board[player.getX() - 1][player.getY()].isWumpus())
                   wumpus.setAlive(false);
                if(player.getX() - 1 >= 0 && board[player.getX() - 1][player.getY()].isMonster2())
                    monster2.setAlive(false);
                shoot_state = false;
            }  else if (fill_state){
                player.setWood(player.getWood() - 1);
                if(player.getX() - 1 >= 0 && board[player.getX() - 1][player.getY()].isPit()) {
                    board[player.getX() - 1][player.getY()].setPit(false);
                    campLabels[player.getX() - 1][player.getY()].setIcon(campIcon); //todo create another icon
                }
                fill_state = false;
            }

        } else if (source == down_b) {
            if(normal_state){
                player.moveDown(board);
            }  else if(lantern_state){
                player.useLantern(board, DOWN);
                updateHidden();
                lantern_state = false;
            }  else if (shoot_state){
                player.setArrows(player.getArrows() - 1);
               if(player.getY() - 1 >= 0 && board[player.getX()][player.getY() - 1].isWumpus())
                   wumpus.setAlive(false);
                if(player.getY() - 1 >= 0 && board[player.getX()][player.getY() - 1].isMonster2())
                    monster2.setAlive(false);
                shoot_state = false;
            }  else if (fill_state){
                player.setWood(player.getWood() - 1);
                if(player.getY() - 1 >= 0 && board[player.getX()][player.getY() - 1].isPit()) {
                    board[player.getX()][player.getY() - 1].setPit(false);
                    updateWind(player.getX(), player.getY() - 1);
                    campLabels[player.getX()][player.getY() - 1].setIcon(campIcon); //todo create another icon
                }
                fill_state = false;
            }

        }  else if (source == gold_b) {
            board[player.getX()][player.getY()].setGold(false);
            player.setGold(1);
            gold_b.setVisible(false);
            gold_b.setEnabled(false);

        } else if (source == wood_b) {
            board[player.getX()][player.getY()].setWood(false);
            player.setWood(player.getWood() + 1);
            wood_b.setVisible(false);
            wood_b.setEnabled(false);

        }  else if (source == drop_b){

        }  else if (source == arrow_b){
            player.setArrows(player.getArrows() + 1);
            board[player.getX()][player.getY()].setWood(false);
        }  else if (source == shoot_b){
            shoot_state = true;
        }  else if (source == fill_b){
            fill_state = true;
        }

        if(!lantern_state && !shoot_state && !fill_state){
            normal_state = true;
        }  else normal_state = false;


        if(!normal_state) hideNonDirectionButtons();
        updateConsoleAndButtons();
        this.updateBoard();
        if(normal_state) monsterTurn();
    }

    public void removeWumpus()
    {
        board[wumpus.getX()][wumpus.getY()].setWumpus(false);
        campLabels[wumpus.getX()][wumpus.getY()].setIcon(campIcon);
    }

    public void removeMonster2()
    {

        board[monster2.getX()][monster2.getY()].setWumpus(false);
        campLabels[monster2.getX()][monster2.getY()].setIcon(campIcon);
    }

    public void updateWind(int x, int y)
    {
        if(x + 1 < board.length) board[x+1][y].setWindy(false); //todo false positives here
        if(y + 1 < board.length) board[x][y+1].setWindy(false);
        if(x - 1 >= 0) board[x-1][y].setWindy(false);
        if(y - 1 >= 0) board[x][y-1].setWindy(false);
    }
    public void setRand(Random rand) {
        this.rand = rand;
    }

}

