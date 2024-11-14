package com.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JPanel {

    private int x = 100;  
    private int y = 100;  
    private final int cursorHeight = 5;  
    private final int cursorWidth = 10;  

    public App() {
        setBackground(Color.BLACK);  
        setPreferredSize(new Dimension(800, 600));  
        setFocusable(true);  
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) {
                    y -= 10;
                } else if (key == KeyEvent.VK_DOWN) {
                    y += 10;
                } else if (key == KeyEvent.VK_LEFT) {
                    x -= 10;
                } else if (key == KeyEvent.VK_RIGHT) {
                    x += 10;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);  
        g.fillRect(x, y, cursorWidth, cursorHeight);  
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Move Cursor");
        App panel = new App();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
