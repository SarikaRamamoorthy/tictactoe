package com.tictactoe.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tictactoe.grid.Grid.Choice;

public class Footer extends JPanel {
    String message;
    char currentPlayer;
    public Footer() {
        super();   
        currentPlayer = '?';
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(Styles.BOARD_WIDTH, 125));
    }

    /**
     * Overrides the paintComponent method to display the footer content, 
     * including controls and the current player's turn.
     *
     * @param graphics - Graphics object for rendering the component.
     */  

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font(Styles.DEFAULT_FONT, Font.BOLD, 10));
        if (this.message != null) {
            graphics.drawString(this.message, 210, 10);
        }
        graphics.drawString("New Game(N)    Exit(E)                                Current Player : "+currentPlayer, 5, 110);
    }

    /**
     * This method sets the win or draw message
     * @param message - The message to be displayed
     * 
     */
    
    public void setPopupMessage(String message) {
        this.message = message;
        repaint();
    }
    
    /**
     * This method sets the current player
     * @param currentPlayer - The current player of the game
     * 
     */
    
    public void setCurrentPlayer(Choice currentPlayer) {
        this.currentPlayer = (currentPlayer == Choice.X )? 'X' : 'O';
        repaint();
    }

    /**
     * This method resets the displayed message
     * 
     */
    
    public void resetMessage() {
        this.message = null;
        repaint();
    }
    
    /**
     * This method resets the displayed current player
     * 
     */

    public void resetCurrentPlayer() {
        this.currentPlayer = '?';
        repaint();
    }
}
