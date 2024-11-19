package com.tictactoe.engine;

import com.tictactoe.grid.Grid;
import com.tictactoe.player.Player;

public class Engine extends Player{
    
    public Engine(Grid.Choice choice) {
        super(choice);
    }

    public int minimax(int x, int y, boolean isMaximizier, int depth) {

        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;

        if (depth == 0 || isGameOver()) {
            return checkState();
        }

        if (isMaximizier) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '\0') {
                        max = Math.max(max, minimax(i, j, false, depth - 1));
                    }
                }
            }
            return max;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '\0') {
                        min = Math.min(min, minimax(i, j, false, depth - 1));
                    }
                }
            }
            return min;
        }

    }

    public boolean isGameOver() {

        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;

        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }

        return true;
    }
    
    public int checkState() {

        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;

        // checking row
        for (int i = 0; i < SIZE ; i++) {
            boolean isWin = true;
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != board[i][j - 1]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[i][0] == 'X' ? 1 : -1;
            }
        }

        // checking column
        for (int i = 0; i < SIZE ; i++) {
            boolean isWin = true;
            for (int j = 1; j < SIZE; j++) {
                if (board[j][i] != board[j - 1][i]) {
                    isWin = false;
                }
            }
            if (isWin) {
                return board[0][i] == 'X' ? 1 : -1;
            }
        }
        
        // checking main diagonal
        boolean isWin = true;
        for (int i = 1; i < SIZE; i++) {
            if (board[i][i] != board[i - 1][i - 1]) {
                isWin = false;
            }
        }
        if (isWin) {
            return board[0][0] == 'X' ? 1 : -1;
        }
        
        // checking alternate diagonal
        isWin = true;
        for (int i = 1; i < SIZE; i++) {
            if (board[i][SIZE - i - 1] != board[i - 1][SIZE - i]) {
                isWin = false;
            }
        }
        if (isWin) {
            return board[0][0] == 'X' ? 1 : -1;
        }

        return 0;

    }
}


//    00   01    02
// // 0    0     0
//    10   11    12
// // 0    0     0
//    20   21    22
// // 0    0     0