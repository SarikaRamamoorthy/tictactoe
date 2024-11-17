package com.tictactoe.player;

import com.tictactoe.grid.Grid;

public class Player {
    int id;
    Grid.Choice choice;
    int score;

    public Player(Grid.Choice choice, int id) {
        this.id = id;
        this.choice = choice;
        this.score = 0;
    }

    public void nextMove(int xPos, int yPos) {
        Grid.setPosition(this.choice, xPos, yPos);
    }
}
