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

class CustomFont {
    static HashMap<TextAttribute, Integer> underlineOn = new HashMap<TextAttribute, Integer>();
    static {
        underlineOn.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_DASHED);
    }
}

public class MainScreen extends JPanel {
    boolean select = true;
    public MainScreen() {
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(500, 500));
        this.setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> setSelection(true);
                    case KeyEvent.VK_DOWN -> setSelection(false);
                }
            }
        });
    }

    public void setSelection(boolean selection) {
        this.select = selection;
        repaint();
    }

    public void toggleSelection(Graphics g, boolean isSelected) {
        if (isSelected) {
            g.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 12).deriveFont(CustomFont.underlineOn));
        } else {
            g.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 12));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 40));
        g.drawString("XO", 210, 150);
        toggleSelection(g, select);
        g.drawString("1. One Player", 190, 280);
        toggleSelection(g, !select);
        g.drawString("2. Two Player", 190, 300);
    }
}
