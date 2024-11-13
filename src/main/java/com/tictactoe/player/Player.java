package com.tictactoe.player;

import com.tictactoe.grid.Grid;

public class Player {
    int id;
    Grid.Choice choice;

    public Player(Grid.Choice choice, int id) {
        this.id = id;
        this.choice = choice;
    }

    public void nextMove(int xPos, int yPos) {
        Grid.setPosition(this.choice, xPos, yPos);
    }
}
 