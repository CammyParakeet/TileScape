package com.parakeetstudios.tilescape.game.board;

import org.jetbrains.annotations.NotNull;

public record BoardPosition(int file, int rank) {

    public BoardPosition translate() {
        return new BoardPosition(0,0);
    }

    public BoardPosition translate(int steps) {
        return new BoardPosition(0,0);
    }

}
