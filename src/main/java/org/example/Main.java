package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;
    static final int DOWN = 4;
    private static int width;
    private static int height;
    private static int size;
    public static void main(String[] args) {
        createOptionsGUI();
    }


    public static void createOptionsGUI()
    {
        JFrame inicio = new JFrame();
        inicio.setTitle("Escolha resolucao e tamanho do jogo");
        inicio.setLayout(new GridLayout(1,2));

        JTextField width_t = new JTextField();
        width_t.setText("1920");
        width_t.setVisible(true);

        JTextField height_t = new JTextField();
        height_t.setText("1080");
        height_t.setVisible(true);

        JButton ok = new JButton("OK");
        ok.setVisible(true);

        JTextField size_t = new JTextField();
        size_t.setText("15");
        size_t.setVisible(true);


        inicio.add(width_t);
        inicio.add(height_t);
        inicio.add(size_t);
        inicio.add(ok);
        inicio.setVisible(true);
        inicio.setBounds(0,0, 400, 100);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ok.addActionListener(e -> {

            if (e.getSource() == ok) {
                width = Integer.parseInt(width_t.getText());
                height = Integer.parseInt(height_t.getText());
                size = Integer.parseInt(size_t.getText());
                inicio.setVisible(false);
                inicio.dispose();
                if(size > 4) startGame();
                else{
                    System.exit(0);
                    //todo message
                }

            }
        });
    }
    public static void startGame()
    {
        Random rand = new Random();
        Game game = new Game(width, height, size);
        game.setRand(rand);

        game.GenerateBoard();
        game.playerTurn();
    }
}
