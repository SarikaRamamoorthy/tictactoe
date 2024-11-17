package com.tictactoe.game;

import com.tictactoe.grid.Grid;
import com.tictactoe.player.Player;

public class Game {
    public Game(int gridSize) {
        Grid.initializeGrid(3);
    }
    
    public void start() {
        int mode = getGameMode();
        while(mode != 3) {
            if (mode == 1) {
                onePlayer();
            }
            else if(mode == 2){
                twoPlayer();
            }
            mode = getGameMode();
        }

    }

    public void end() {

    }

    public void getNextMove() {

    }

    public int getGameMode() {
        System.out.println("1. One Player");
        System.out.println("2. Two Player");
        System.out.println("3. Exit");
        System.out.println("Enter your choice (1/2/3): ");
        // validation
        // get input
        int mode = 1;
        return mode;
    }

    public void onePlayer() {
        System.out.println("Entering one player mode...");
        // get input
        char c = 'X';
        Grid.Choice choice = c == 'X' ? Grid.Choice.X : Grid.Choice.O;
        Player player = new Player(choice);
    }

    public void twoPlayer() {

    }
}
