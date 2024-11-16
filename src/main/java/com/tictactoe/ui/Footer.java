package com.tictactoe.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Footer extends JPanel {
    String message;
    public Footer() {
        super();       
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(500, 125));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 10));
        if (this.message != null) {
            graphics.drawString(this.message, 210, 10);
        }
        graphics.drawString("New Game(N)    Exit(E)                                Current Player : "+"X", 5, 110);
    }

    public void setPopupMessage(String message) {
        this.message = message;
        repaint();
    }

    public void resetMessage() {
        this.message = null;
    }
}
