package com.tictactoe.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Scorecard extends JPanel {
    public Scorecard() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(500, 50));
    }

    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score : "+"0 / 0", 210, 40);
    }
}
