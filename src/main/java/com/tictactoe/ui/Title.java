package com.tictactoe.ui;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {
    public Title() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(Styles.BOARD_WIDTH, Styles.TITLE_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Styles.DEFAULT_FONT, Font.BOLD, 15));
        graphics.drawString("XO Game", 210, 15);
    }
}