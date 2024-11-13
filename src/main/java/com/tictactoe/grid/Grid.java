package com.tictactoe.grid;

public class Grid implements Decoration {
    public enum Choice {X, O};
    static int SIZE;
    static int[][] matrix;

    public static void initializeGrid(int n) {
        SIZE = n;
        matrix = new int[n][n];
    }

    public static void display() {
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                System.out.print(matrix[i][j] + " ");
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
            (matrix[xPos][yPos] == 0)
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
            matrix[xPos][yPos] = 1;
        } else {
            matrix[xPos][yPos] = -1;
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
        for(int i = xPos - 1 ; i >= 0 ; i--) {
            if (matrix[xPos][yPos] != matrix[i][yPos]) {
                return false;
            }
        }

        for(int i = xPos + 1 ; i < SIZE ; i++) {
            if (matrix[xPos][yPos] != matrix[i][yPos]) {
                return false;
            }
        }
        
        // check column conditions
        for(int i = yPos - 1 ; i >= 0 ; i--) {
            if (matrix[xPos][yPos] != matrix[xPos][i]) {
                return false;
            }
        }
        
        for(int i = yPos + 1 ; i < SIZE ; i++) {
            if (matrix[xPos][yPos] != matrix[xPos][i]) {
                return false;
            }
        }
        
        // check main diagonal conditions
        if(xPos == yPos) {
            
            for(int i = xPos - 1 ; i >= 0 ; i--) {
                if (matrix[xPos][yPos] != matrix[i][i]) {
                    return false;
                }
            }
    
            for(int i = xPos + 1 ; i < SIZE ; i++) {
                if (matrix[xPos][yPos] != matrix[i][i]) {
                    return false;
                }
            }
        }

        // check alternate diagonal conditions
        if(xPos + yPos == SIZE - 1) {
            
            for(int i = xPos - 1 ; i >= 0 ; i--) {
                if (matrix[xPos][yPos] != matrix[i][yPos - i]) {
                    return false;
                }
            }
    
            for(int i = xPos + 1 ; i < SIZE ; i++) {
                if (matrix[xPos][yPos] != matrix[i][yPos - i]) {
                    return false;
                }
            }
        }

        return true;

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
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}