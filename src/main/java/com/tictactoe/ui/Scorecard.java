package com.tictactoe.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Scorecard extends JPanel {

    private int playerOneScore; 
    private int playerTwoScore; 

    public Scorecard() {
        playerOneScore = 0;
        playerTwoScore = 0;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(500, 50));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score : "+playerOneScore+" / "+playerTwoScore, 210, 40);
    }

    public void updateScorecard(int playerOneScore, int playerTwoScore) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        repaint();
    }

    public void resetScorecard() {
        playerOneScore = 0;
        playerTwoScore = 0;
        repaint();
    }
}