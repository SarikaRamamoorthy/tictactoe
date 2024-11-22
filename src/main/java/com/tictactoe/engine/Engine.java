package com.tictactoe.engine;

import com.tictactoe.grid.Grid;
import com.tictactoe.grid.Grid.Choice;
import com.tictactoe.player.Player;

public class Engine extends Player{

    public Engine(Grid.Choice choice) {
        super(choice);
    }
    
    
    /**
     * This method finds the next best move of the engine
     * 
     * @return - an array with position for the next move
     */
    
    @Override
    public int[] nextMove() {

        int SIZE = Grid.getBoardSize();
        int score = Integer.MIN_VALUE;
        int[] position = new int[2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Grid.isPositionEmpty(i, j)) {
                    Grid.setPosition(choice, i, j);
                    int value = minimax(false, 9);
                    Grid.setPosition(Grid.Choice.EMPTY, i, j);
                    if (value > score) { 
                        score = value;
                        position[0] = i;
                        position[1] = j;
                    }
                }
            }
        }
        
        return position;
    }

    /**
     * This method finds the optimal value a maximizer can obtain
     * @param isMaximizer - is true if current move is of maximizer 
     * @param depth - remaining depth of the game tree to explore
     * 
     * @return - If current move is maximizer, returns the maximum attainable value else returns the minimum attainable value
     */
    
    
    public int minimax(boolean isMaximizer, int depth) {

        int SIZE = Grid.getBoardSize();

        int state = checkState();
        if (state != 0 || depth == 0 || isGameOver()) {
            return state;
        }

        if (isMaximizer) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (Grid.isPositionEmpty(i, j)) {
                        Grid.setPosition(this.choice, i, j);
                        max = Math.max(max, minimax(false, depth - 1));
                        Grid.setPosition(Grid.Choice.EMPTY, i, j);
                    }
                }
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (Grid.isPositionEmpty(i, j)) {
                        Grid.setPosition(this.choice == Grid.Choice.X ? Grid.Choice.O : Grid.Choice.X, i, j);
                        min = Math.min(min, minimax(true, depth - 1));
                        Grid.setPosition(Grid.Choice.EMPTY, i, j);
                    }
                }
            }
            return min;
        }

    }

    /**
     * This method checks if there is any empty space in the grid
     * 
     * @return - true if not empty spaces remain, false otherwise
     */
    

    public boolean isGameOver() {

        int SIZE = Grid.getBoardSize();

        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Grid.isPositionEmpty(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This method checks the win and draw state of the players
     * 
     * @return - 1 if engine wins, -1 if opponent wins and 0 if draw or still going 
     */
    
    
    public int checkState() {

        char[][] board = Grid.getBoard();
        int SIZE = Grid.getBoardSize();
        char winChar = this.choice == Choice.X ? 'X' : 'O';

        // checking row
        for (int i = 0; i < SIZE ; i++) {
            boolean isWin = true;
            if (Grid.isPositionEmpty(i, 0)) continue;
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != board[i][j - 1]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[i][0] == winChar ? 1 : -1;
            }
        }
        
        // checking column
        for (int i = 0; i < SIZE ; i++) {
            boolean isWin = true;
            if (Grid.isPositionEmpty(0, i)) continue;
            for (int j = 1; j < SIZE; j++) {
                if (board[j][i] != board[j - 1][i]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[0][i] == winChar ? 1 : -1;
            }
        }
        
        // checking main diagonal
        if (!Grid.isPositionEmpty(0, 0)) {
            boolean isWin = true;
            for (int i = 1; i < SIZE; i++) {
                if (board[i][i] != board[i - 1][i - 1]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[0][0] == winChar ? 1 : -1;
            }
        }
        
        // checking alternate diagonal
        if (!Grid.isPositionEmpty(0, SIZE - 1)) {
            boolean isWin = true;
            for (int i = 1; i < SIZE; i++) {
                if (board[i][SIZE - i - 1] != board[i - 1][SIZE - i]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[0][SIZE - 1] == winChar ? 1 : -1;
            }
        }

        return 0;

    }
}