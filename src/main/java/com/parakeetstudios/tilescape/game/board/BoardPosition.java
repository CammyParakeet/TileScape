package com.parakeetstudios.tilescape.game.board;

/**
 * @author Cammy
 * @version 1.0
 */

public record BoardPosition(int file, int rank) {

    public BoardPosition translate() {
        return new BoardPosition(0,0);
    }

    public BoardPosition translate(int steps) {
        return new BoardPosition(0,0);
    }


}
