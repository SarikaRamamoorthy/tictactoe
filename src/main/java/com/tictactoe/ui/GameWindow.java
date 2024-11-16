package com.tictactoe.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GameWindow extends JFrame {
    public GameWindow() {
        Board board = new Board();
        Title title = new Title();
        this.setTitle("Tic Tac Toe");
        this.setBounds(0, 0, 500, 520);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setResizable(false);
        
    }
}
