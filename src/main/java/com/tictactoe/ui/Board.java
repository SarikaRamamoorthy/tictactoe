package com.tictactoe.ui;

import javax.swing.*;

import com.tictactoe.grid.Grid;
import com.tictactoe.grid.Grid.Choice;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private static final int XOFFSET = 160;
    private static final int YOFFSET = 135;
    private static final int PADDING = 10;  
    private static final int GRID_COUNT = Grid.SIZE;  
    private static final int CELL_SIZE = 55;  

    private int cursorRow = 0;  
    private int cursorCol = 0;  
    private int prevCursorRow = 0;  
    private int prevCursorCol = 0;  

    private char[][] board = Grid.board;  
    private Choice currentPlayer = Choice.X;
    private boolean gameOver = false; 

    Footer footer;  
    Scorecard scorecard;

    public Board() {
        footer = new Footer();
        scorecard = new Scorecard();

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setSize(new Dimension(GRID_COUNT * CELL_SIZE + XOFFSET, GRID_COUNT * CELL_SIZE + YOFFSET));  
        setFocusable(true); 
        add(scorecard, BorderLayout.NORTH);
        add(footer, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                prevCursorRow = cursorRow;
                prevCursorCol = cursorCol;

                int key = e.getKeyCode();
                if (!gameOver) {
                    switch (key) {
                        case KeyEvent.VK_UP -> cursorRow = (cursorRow - 1 + GRID_COUNT) % GRID_COUNT;
                        case KeyEvent.VK_DOWN -> cursorRow = (cursorRow + 1) % GRID_COUNT;
                        case KeyEvent.VK_LEFT -> cursorCol = (cursorCol - 1 + GRID_COUNT) % GRID_COUNT;
                        case KeyEvent.VK_RIGHT -> cursorCol = (cursorCol + 1) % GRID_COUNT;
                        case KeyEvent.VK_ENTER -> markCell();
                    }
                    repaintCell(prevCursorRow, prevCursorCol);  
                    repaintCell(cursorRow, cursorCol);  
                }
                
                switch (key) {
                    case KeyEvent.VK_N -> newGame();
                    case KeyEvent.VK_E -> exitGame();
                }
                
            }
        });
    }

    private void newGame() {
        Grid.clearBoard();
        this.footer.resetMessage();
        gameOver = false;
    }

    private void exitGame() {
        
    }

    private void markCell() {
        if (board[cursorRow][cursorCol] == '\0') {  
            board[cursorRow][cursorCol] = currentPlayer == Choice.X ? 'X' : 'O';
            currentPlayer = (currentPlayer == Choice.X) ? Choice.O : Choice.X;  
            repaintCell(cursorRow, cursorCol);
            if (Grid.isWinState(cursorRow, cursorCol)) {
                String message = currentPlayer == Choice.X ? "O Wins!!!" : "X Wins!!!";
                footer.setPopupMessage(message);
                gameOver = true;
            } else if (Grid.isDrawState()) {
                footer.setPopupMessage("DRAW!!!");
                gameOver = true;
            } 
        }
    }

    private void repaintCell(int row, int col) {
        int x = col * CELL_SIZE + XOFFSET;
        int y = row * CELL_SIZE + YOFFSET;
        repaint(x, y, CELL_SIZE, CELL_SIZE);  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Draw the grid and the symbols
        for (int row = 0; row < GRID_COUNT; row++) {
            for (int col = 0; col < GRID_COUNT; col++) {
                int x = col * CELL_SIZE + XOFFSET;
                int y = row * CELL_SIZE + YOFFSET;
                
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);  

                if (board[row][col] != '\0') {  
                    g.setFont(new Font("MV Boli", Font.BOLD, 20));
                    g.drawString(String.valueOf(board[row][col]), x + CELL_SIZE / 3, y + 2 * CELL_SIZE / 3);
                }
            }
        }

        // Draw the cursor
        g.drawRect(cursorCol * CELL_SIZE + XOFFSET + PADDING / 2, 
                   cursorRow * CELL_SIZE + YOFFSET + PADDING / 2,
                   CELL_SIZE - PADDING, CELL_SIZE - PADDING);  
    }
}

/*
 * 0 (0 - 1 + 3)
 * 1 
 * 2
 */