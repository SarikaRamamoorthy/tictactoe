package com.tictactoe.ui;

import javax.swing.*;

import com.tictactoe.engine.Engine;
import com.tictactoe.grid.Grid;
import com.tictactoe.grid.Grid.Choice;
import com.tictactoe.player.Player;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    private static final int XOFFSET = 160;
    private static final int YOFFSET = 135;
    private static final int PADDING = 10;  
    private static final int GRID_COUNT = Grid.getBoardSize();  
    private static final int CELL_SIZE = 55;  

    private int cursorRow;  
    private int cursorCol;  
    private int prevCursorRow;  
    private int prevCursorCol;  

    private char[][] board = Grid.getBoard();  
    private Choice currentPlayer;
    private boolean gameOver; 
    private boolean isFirstGame;


    Footer footer;  
    Scorecard scorecard;
    GameWindow gameWindow;
    Player playerOne;
    Player playerTwo;
    boolean isOnePlayerMode;

    public Board(GameWindow gameWindow, boolean isOnePlayerMode) {
        footer = new Footer();
        scorecard = new Scorecard();
        this.gameWindow = gameWindow;
        isFirstGame = true;
        initialize();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(GRID_COUNT * CELL_SIZE + XOFFSET, GRID_COUNT * CELL_SIZE + YOFFSET));  
        this.add(scorecard, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        this.setFocusable(true); 
        this.isOnePlayerMode = isOnePlayerMode;

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
                if(currentPlayer == null) {
                    switch (key) {
                        case KeyEvent.VK_X -> setCurrentPlayer(Choice.X);
                        case KeyEvent.VK_O -> setCurrentPlayer(Choice.O);
                    }
                }
                
                switch (key) {
                    case KeyEvent.VK_N -> newGame();
                    case KeyEvent.VK_E -> exitGame();
                }
                
            }
        });
    }

    public void initialize() {
        cursorRow = 0;
        cursorCol = 0;
        prevCursorRow = 0;
        prevCursorCol = 0;
        gameOver = true;
        currentPlayer = null;
    }

    private void newGame() {
        Grid.clearBoard();
        this.footer.resetMessage();
        this.footer.resetCurrentPlayer();
        initialize();
        repaint();
    }

    private void exitGame() {
        newGame();
        gameWindow.setMainScreen();
    }

    private void markCell() {
        if (board[cursorRow][cursorCol] == '\0') {  
            board[cursorRow][cursorCol] = currentPlayer == Choice.X ? 'X' : 'O';
            if (Grid.isWinState(cursorRow, cursorCol)) {
                if (playerOne.choice == currentPlayer) {
                    playerOne.score++;
                }
                else {
                    playerTwo.score++;
                }
                scorecard.updateScorecard(playerOne.score, playerTwo.score);
                String message = currentPlayer == Choice.X ? "X Wins!!!" : "O Wins!!!";
                footer.setPopupMessage(message);
                gameOver = true;
                return;
            } else if (Grid.isDrawState()) {
                footer.setPopupMessage("DRAW!!!");
                gameOver = true;
                return;
            } 
            currentPlayer = (currentPlayer == Choice.X) ? Choice.O : Choice.X; 
            footer.setCurrentPlayer(currentPlayer); 
            repaintCell(cursorRow, cursorCol);
            if (isOnePlayerMode && currentPlayer == playerTwo.choice) {
                engineMove();
            }
        }
    }

    public void engineMove() {

        int[] position = playerTwo.nextMove();

        if (cursorRow > position[0]) {
            for (int i = cursorRow - 1; i >= position[0]; i--) {
                cursorRow = i;
                repaint();
            }
        }
        else {
            
            for (int i = cursorRow + 1; i <= position[0]; i++) {
                cursorRow = i;
                repaint();
            }
        }
        
        if (cursorCol > position[1]) {
            for (int i = cursorCol - 1; i >= position[1]; i--) {
                cursorCol = i;
                repaint();
            }
        }
        else {
            
            for (int i = cursorCol + 1; i <= position[1]; i++) {
                cursorCol = i;
                repaint();
            }
        }

        markCell();
    }

    private void repaintCell(int row, int col) {
        int x = col * CELL_SIZE + XOFFSET;
        int y = row * CELL_SIZE + YOFFSET;
        repaint(x, y, CELL_SIZE, CELL_SIZE);  
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);

        // Draw the grid and the symbols
        for (int row = 0; row < GRID_COUNT; row++) {
            for (int col = 0; col < GRID_COUNT; col++) {
                int x = col * CELL_SIZE + XOFFSET;
                int y = row * CELL_SIZE + YOFFSET;
                
                graphics.drawRect(x, y, CELL_SIZE, CELL_SIZE);  

                if (board[row][col] != '\0') {  
                    graphics.setFont(new Font("MV Boli", Font.BOLD, 20));
                    graphics.drawString(String.valueOf(board[row][col]), x + CELL_SIZE / 3, y + 2 * CELL_SIZE / 3);
                }
            }
        }
        
        if(currentPlayer == null) {
            graphics.setFont(new Font("JetBrains Mono Regular", Font.BOLD, 10));
            graphics.drawString("Choose X or O from keyboard", XOFFSET - 5, (CELL_SIZE * GRID_COUNT) + YOFFSET + 30);
        }

        // Draw the cursor
        graphics.drawRect(cursorCol * CELL_SIZE + XOFFSET + PADDING / 2, 
                   cursorRow * CELL_SIZE + YOFFSET + PADDING / 2,
                   CELL_SIZE - PADDING, CELL_SIZE - PADDING);  
    }

    public void setCurrentPlayer(Choice choice) {
        currentPlayer = choice;
        gameOver =  false;
        if(isFirstGame) {
            isFirstGame = false;
            playerOne = new Player(currentPlayer);
            if (isOnePlayerMode) {
                playerTwo = new Engine(currentPlayer == Choice.X ? Choice.O : Choice.X);
                if (playerTwo.choice == Choice.X) {
                    currentPlayer = Choice.X;
                    engineMove();
                }
            } else {
                playerTwo = new Player(currentPlayer == Choice.X ? Choice.O : Choice.X);
            }
        }
        else {
            playerOne.choice = currentPlayer;
            playerTwo.choice = currentPlayer == Choice.X ? Choice.O : Choice.X;
        }
        footer.setCurrentPlayer(choice); 
        repaint();
    }
}