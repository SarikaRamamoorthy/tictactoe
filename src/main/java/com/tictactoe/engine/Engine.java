package com.tictactoe.engine;

import com.tictactoe.grid.Grid;
import com.tictactoe.grid.Grid.Choice;
import com.tictactoe.player.Player;

public class Engine extends Player{
    
    public Engine(Grid.Choice choice) {
        super(choice);
    }

    @Override
    public int[] nextMove() {

        int SIZE = Grid.SIZE;
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
    
    char[][] board = Grid.board;
    public int minimax(boolean isMaximizier, int depth) {

        int SIZE = Grid.SIZE;

        int state = checkState();
        if (state != 0 || depth == 0 || isGameOver()) {
            return state;
        }

        if (isMaximizier) {
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

    public boolean isGameOver() {

        int SIZE = Grid.SIZE;

        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Grid.isPositionEmpty(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
    
    public int checkState() {

        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;
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