package com.tictactoe;

import com.tictactoe.grid.Grid;
import com.tictactoe.ui.GameWindow;

public class App {

    public static void main(String[] args) {
        Grid.initializeGrid(3);
        GameWindow game = new GameWindow();
        game.runGame();
    }
}