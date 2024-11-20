package com.tictactoe.player;

import com.tictactoe.grid.Grid;

public class Player {
    public Grid.Choice choice;
    public int score;

    public Player(Grid.Choice choice) {
        this.choice = choice;
        this.score = 0;
    }

    public int[] nextMove() {
        return new int[]{};
    }
}
