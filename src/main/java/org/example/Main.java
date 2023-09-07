package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int UP = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;
    static final int DOWN = 4;
    private static int width;
    private static int height;
    private static boolean clear;
    public static void main(String[] args) {
        JFrame inicio = new JFrame();
        inicio.setTitle("Escolha resolucao");
        inicio.setLayout(new GridLayout(1,2));
        JTextField width_t = new JTextField();
        width_t.setText("1024");
        width_t.setVisible(true);
        JTextField height_t = new JTextField();
        height_t.setText("720");
        height_t.setVisible(true);
        JButton ok = new JButton("OK");
        ok.setVisible(true);

        inicio.add(width_t);
        inicio.add(height_t);
        inicio.add(ok);
        inicio.setVisible(true);
        inicio.setBounds(0,0, 300, 100);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(!clear) {
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (e.getSource() == ok) {
                        width = Integer.parseInt(width_t.getText());
                        height = Integer.parseInt(height_t.getText());
                        clear = true;
                        inicio.setVisible(false);
                        inicio.dispose();
                    }
                }
            });
        }

        Random rand = new Random();
        Game game = new Game(width, height);
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