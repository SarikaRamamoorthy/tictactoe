package com.tictactoe.engine;

import com.tictactoe.grid.Grid;
import com.tictactoe.grid.Grid.Choice;
import com.tictactoe.player.Player;

/**
 * AI Engine using the Minimax algorithm for Tic Tac Toe.
 */
public class Engine extends Player {

    public Engine(Grid.Choice choice) {
        super(choice);
    }

    @Override
    public int[] nextMove() {
        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;

        int bestScore = Integer.MIN_VALUE;
        int[] bestPosition = new int[2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Check if the cell is empty
                if (board[i][j] == '\0') {
                    board[i][j] = this.choice == Choice.X ? 'X' : 'O'; // Make the move
                    int currentScore = minimax(false, 9);
                    board[i][j] = '\0'; // Undo the move

                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestPosition[0] = i;
                        bestPosition[1] = j;
                    }
                }
            }
        }
        return bestPosition;
    }

    /**
     * Minimax algorithm to evaluate the best move.
     *
     * @param isMaximizer True if it's the maximizer's turn, false otherwise
     * @param depth       Remaining depth to explore
     * @return Score of the board state
     */
    private int minimax(boolean isMaximizer, int depth) {
        int state = checkState();

        // If terminal state or depth reached
        if (state != 0 || depth == 0 || isGameOver()) {
            return state;
        }

        int SIZE = Grid.SIZE;
        char[][] board = Grid.board;

        if (isMaximizer) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = this.choice == Choice.X ? 'X' : 'O'; // Maximizer's move
                        maxEval = Math.max(maxEval, minimax(false, depth - 1));
                        board[i][j] = '\0'; // Undo move
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = this.choice == Choice.X ? 'O' : 'X'; // Minimizer's move
                        minEval = Math.min(minEval, minimax(true, depth - 1));
                        board[i][j] = '\0'; // Undo move
                    }
                }
            }
            return minEval;
        }
    }

    /**
     * Checks if the game is over by determining if there are empty cells.
     *
     * @return True if no moves are available, false otherwise
     */
    private boolean isGameOver() {
        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks the current state of the board.
     *
     * @return 1 if the AI wins, -1 if the opponent wins, 0 for no result yet
     */
    private int checkState() {
        char[][] board = Grid.board;
        int SIZE = Grid.SIZE;
        char winChar = this.choice == Choice.X ? 'X' : 'O';

        // Check rows and columns for a win
        for (int i = 0; i < SIZE; i++) {
            if (isWinningLine(board[i][0], board[i])) return board[i][0] == winChar ? 1 : -1;

            char[] column = new char[SIZE];
            for (int j = 0; j < SIZE; j++) {
                column[j] = board[j][i];
            }
            if (isWinningLine(column[0], column)) return column[0] == winChar ? 1 : -1;
        }

        // Check diagonals
        char[] mainDiagonal = new char[SIZE];
        char[] antiDiagonal = new char[SIZE];
        for (int i = 0; i < SIZE; i++) {
            mainDiagonal[i] = board[i][i];
            antiDiagonal[i] = board[i][SIZE - 1 - i];
        }
        if (isWinningLine(mainDiagonal[0], mainDiagonal)) return mainDiagonal[0] == winChar ? 1 : -1;
        if (isWinningLine(antiDiagonal[0], antiDiagonal)) return antiDiagonal[0] == winChar ? 1 : -1;

        return 0;
    }

    /**
     * Helper function to check if all elements in a line are the same and non-empty.
     *
     * @param first Element to compare
     * @param line  Array representing a row, column, or diagonal
     * @return True if the line is a winning line, false otherwise
     */
    private boolean isWinningLine(char first, char[] line) {
        if (first == '\0') return false;
        for (char cell : line) {
            if (cell != first) return false;
        }
        return true;
    }
}
