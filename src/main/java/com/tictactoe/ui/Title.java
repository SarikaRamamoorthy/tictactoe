package com.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {
    public Title() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 15));
        g.drawString("XO Game", 210, 15);
    }
}