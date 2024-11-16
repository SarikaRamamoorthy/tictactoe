package com.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {
    public Title() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("XO Game", 225, 15);
    }
}