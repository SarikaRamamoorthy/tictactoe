package com.tictactoe.grid;

enum Choice {X, O};

public class Grid implements Decoration {
    static int SIZE;
    static int[][] matrix;

    public void initializeGrid(int n) {
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
}
