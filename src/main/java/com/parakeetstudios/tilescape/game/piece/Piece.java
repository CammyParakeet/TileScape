package com.parakeetstudios.tilescape.game.piece;

import org.bukkit.Location;

public interface Piece {

    // PieceType getType();

    // PieceColor getColor();

    /**
     *  Minecraft Logic
     */

    void spawnAt(Location location);

    void moveTo(Location location);

    void remove();

}
