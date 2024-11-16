package com.tictactoe.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private static final int OFFSET = 150;
    private static final int PADDING = 10;  
    private static final int GRID_SIZE = 3;  
    private static final int CELL_SIZE = 50;  

    private int cursorRow = 0;  
    private int cursorCol = 0;  
    private int prevCursorRow = 0;  
    private int prevCursorCol = 0;  

    private char[][] board = new char[GRID_SIZE][GRID_SIZE];  
    private char currentPlayer = 'X';  

    public Board() {
        setBackground(Color.BLACK);
        setSize(new Dimension(GRID_SIZE * CELL_SIZE + OFFSET, GRID_SIZE * CELL_SIZE + OFFSET));  
        setFocusable(true); 

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                prevCursorRow = cursorRow;
                prevCursorCol = cursorCol;

                int key = e.getKeyCode();
                switch (key) {
                    case KeyEvent.VK_UP -> cursorRow = (cursorRow - 1 + GRID_SIZE) % GRID_SIZE;
                    case KeyEvent.VK_DOWN -> cursorRow = (cursorRow + 1) % GRID_SIZE;
                    case KeyEvent.VK_LEFT -> cursorCol = (cursorCol - 1 + GRID_SIZE) % GRID_SIZE;
                    case KeyEvent.VK_RIGHT -> cursorCol = (cursorCol + 1) % GRID_SIZE;
                    case KeyEvent.VK_ENTER -> markCell();
                }

                repaintCell(prevCursorRow, prevCursorCol);  
                repaintCell(cursorRow, cursorCol);  
            }
        });
    }

    private void markCell() {
        if (board[cursorRow][cursorCol] == '\0') {  
            board[cursorRow][cursorCol] = currentPlayer;
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  
            repaintCell(cursorRow, cursorCol);  
        }
    }

    private void repaintCell(int row, int col) {
        int x = col * CELL_SIZE + OFFSET;
        int y = row * CELL_SIZE + OFFSET;
        repaint(x, y, CELL_SIZE, CELL_SIZE);  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Draw the grid and the symbols
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = col * CELL_SIZE + OFFSET;
                int y = row * CELL_SIZE + OFFSET;
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);  

                if (board[row][col] != '\0') {  
                    g.setFont(new Font("MV Boli", Font.BOLD, 20));
                    g.drawString(String.valueOf(board[row][col]), x + CELL_SIZE / 3, y + 2 * CELL_SIZE / 3);
                }
            }
        }

        // Draw the cursor
        g.setColor(Color.WHITE);  
        g.drawRect(cursorCol * CELL_SIZE + OFFSET + PADDING / 2, 
                   cursorRow * CELL_SIZE + OFFSET + PADDING / 2,
                   CELL_SIZE - PADDING, CELL_SIZE - PADDING);  
    }
}
