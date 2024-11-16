package com.tictactoe.grid;

public class Grid implements Decoration {
    public enum Choice {X, O};
    public static int SIZE;
    public static char[][] board;   

    public static void initializeGrid(int n) {
        SIZE = n;
        board = new char[n][n];
    }

    public static void display() {
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method checks if a given position is valid or not
     * @param xPos - Denotes the x-axis position
     * @param yPos - Denotes the y-axis position 
     * 
     * @return - Whether the position is a valid position in the grid
     */
    
    public static boolean isValidPostition(int xPos, int yPos) {
        return (
            (xPos >= 0 && xPos < SIZE) && 
            (yPos >= 0 && yPos < SIZE) && 
            (board[xPos][yPos] == 0)
        );
    }

    /**
     * This method sets X or O in the position specified
     * @param choice - Denotes the X or O based on the user's choice
     * @param xPos - Denotes the x-axis position
     * @param yPos - Denotes the y-axis position 
     * 
     * @return - Whether the choice is set in the position or not
     */
    
    public static boolean setPosition(Choice choice, int xPos, int yPos) {
        if(!isValidPostition(xPos, yPos)) {
            return false;
        }
        if (choice == Choice.X) {
            board[xPos][yPos] = 'X';
        } else {
            board[xPos][yPos] = 'O';
        }
        return true;
    }
    
    /**
     * This method checks whether the player wins or not
     * @param xPos - Denotes the x-axis position
     * @param yPos - Denotes the y-axis position 
     * 
     * @return - Whether the player wins in the game
     */

    public static boolean isWinState(int xPos, int yPos) {

        // check row conditions
        boolean flag = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[xPos][yPos] != board[i][yPos]) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }
        
        // check column conditions
        flag = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[xPos][yPos] != board[xPos][i]) {
                flag = false;
            }
        }
        
        if (flag) {
            return true;
        }

        // check main diagonal conditions
        if(xPos == yPos) {
            
            flag = true;
            for(int i = 0 ; i < SIZE ; i++) {
                if (board[xPos][yPos] != board[i][i]) {
                    flag = false;
                }
            }
    
            if (flag) {
                return true;
            }
        }

        // check alternate diagonal conditions
        if(xPos + yPos == SIZE - 1) {
            
            flag = true;
            for (int i = 0; i < SIZE; i++) {
                if (board[xPos][yPos] != board[i][SIZE - i - 1]) {
                    flag = false;
                }
            }
        }

        return flag;

    }

    /**
     * This method checks whether the game draws or not
     * @param xPos - Denotes the x-axis position
     * @param yPos - Denotes the y-axis position 
     * 
     * @return - Whether the game draws
     */

    public static boolean isDrawState() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}