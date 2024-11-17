package com.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {
    public Title() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 20));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 15));
        graphics.drawString("XO Game", 210, 15);
    }
}