package com.tictactoe.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    boolean GAME_STOP = false;
    boolean boardDisplayed = false;
    boolean mainScreenDisplayed = false;
    MainScreen mScreen;

    public GameWindow() {
        this.setTitle("Tic Tac Toe");
        this.setBounds(0, 0, 500, 520);
        this.setResizable(false);
    }
    
    public void runGame() {
        setMainScreen();
        // setBoard();
    }
    
    public void setMainScreen() {
        mScreen = new MainScreen(this);
        clrScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(mScreen, BorderLayout.NORTH);
        this.setVisible(true);
        mScreen.requestFocusInWindow();
    }

    public void clrScreen() {
        this.getContentPane().removeAll();
    }
    
    public void setBoard() {
        Title title = new Title();
        Board board = new Board(this);
        clrScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
        board.requestFocusInWindow();
    }

}
