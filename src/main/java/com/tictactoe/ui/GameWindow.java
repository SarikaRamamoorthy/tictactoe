package com.tictactoe.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    MainScreen mScreen;

    public GameWindow() {
        this.setTitle("Tic Tac Toe");
        this.setBounds(0, 0, Styles.BOARD_WIDTH, Styles.BOARD_LENGTH + Styles.TITLE_HEIGHT);
        this.setResizable(false);
    }
    
    /**
     * This method starts the game by displaying main screeen
     */

    public void runGame() {
        setMainScreen();
    }

    /**
     * This method adds the main screen instance to the frame
     */
    
    public void setMainScreen() {
        mScreen = new MainScreen(this);
        clrScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(mScreen, BorderLayout.NORTH);
        this.setVisible(true);
        mScreen.requestFocusInWindow();
    }

    /**
     * This method is used to clear the frame
     */

    public void clrScreen() {
        this.getContentPane().removeAll();
    }

    /**
     * This method adds the instance of title and board to the frame
     * 
     * @param isOnePlayerMode - true if one player mode is selected, 
     * false if two player mode is selected
     */
    
    public void setBoard(boolean isOnePlayerMode) {
        Title title = new Title();
        Board board = new Board(this, isOnePlayerMode);
        clrScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(title, BorderLayout.NORTH);
        this.add(board, BorderLayout.CENTER);
        this.setVisible(true);
        board.requestFocusInWindow();
    }

}
