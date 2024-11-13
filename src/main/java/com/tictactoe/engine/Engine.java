package com.tictactoe.engine;

import com.tictactoe.grid.Grid;
import com.tictactoe.player.Player;

public class Engine extends Player{
    Grid.Choice choice;

    public Engine(Grid.Choice choice) {
        super(choice, -1);
        this.choice = choice;
    }
}
