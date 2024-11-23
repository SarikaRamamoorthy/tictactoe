package com.tictactoe.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import javax.swing.JPanel;

public class MainScreen extends JPanel {

    // Applies Underline property to the string in drawString method
    static HashMap<TextAttribute, Integer> underlineOn = new HashMap<TextAttribute, Integer>();
    static {
        underlineOn.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_DASHED);
    }

    boolean select = true, isSelected = false;

    /**
     * Constructs a main screen object with a specified parent window.
     * 
     * @param parentWindow - The GameWindow instance that contains this MainScreen.
     */
    
    public MainScreen(GameWindow parentWindow) {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(Styles.BOARD_WIDTH, Styles.BOARD_LENGTH));
        this.setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> setSelection(true);
                    case KeyEvent.VK_DOWN -> setSelection(false);
                    case KeyEvent.VK_ENTER -> parentWindow.setBoard(select);
                }
            }
        });
    }

    /**
     * Enables the selection functionality by setting isSelected to true if one player mode,
     * false if two player mode is selected
     * 
     */ 

    public void setModeSelection() {
        isSelected = true;
    }

    /**
     * Updates the current mode selection based on user input (up or down arrow keys).
     * Repaints the component to reflect the change in selection.
     *
     * @param selection - true if one up arrow is pressed, false is down arrow is pressed
     */ 

    public void setSelection(boolean selection) {
        this.select = selection;
        repaint();
    }

    /**
     * Overrides the paintComponent method to display styles to the selected component.
     *
     * @param graphics - Graphics object for rendering the component.
     * @param isSelected - true if one player mode, false otherwise
     */  

    public void toggleSelection(Graphics graphics, boolean isSelected) {
        if (isSelected) {
            graphics.setFont(new Font(Styles.DEFAULT_FONT, Font.BOLD, 12).deriveFont(underlineOn));
        } else {
            graphics.setFont(new Font(Styles.DEFAULT_FONT, Font.BOLD, 12));
        }
    }

    /**
     * Overrides the paintComponent method to display the main screen content, 
     * including title and the player mode selection.
     *
     * @param graphics - Graphics object for rendering the component.
     */  

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font(Styles.DEFAULT_FONT, Font.BOLD, 40));
        graphics.drawString("XO", 210, 150);
        toggleSelection(graphics, select);
        graphics.drawString("1. One Player", 190, 280);
        toggleSelection(graphics, !select);
        graphics.drawString("2. Two Player", 190, 300);
    }
}
