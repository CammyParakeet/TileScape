package com.parakeetstudios.tilescape.game.piece;

import org.bukkit.Color;

/**
 * @author Cammy
 * @version 1.0
 */

public enum SimplePieceColor implements PieceColor {

    WHITE(true),
    BLACK(false);

    private final boolean isStarter;

    SimplePieceColor(boolean isWhite) {
        this.isStarter = isWhite;
    }

    @Override
    public boolean isStarter() {
        return isStarter;
    }

    //TODO allow color to be from config?
    @Override
    public Color displayColor() {
        return isStarter ? Color.WHITE : Color.BLACK;
    }
}
