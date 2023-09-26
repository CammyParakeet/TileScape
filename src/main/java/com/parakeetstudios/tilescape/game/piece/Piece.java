package com.parakeetstudios.tilescape.game.piece;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Piece {

    // PieceType getType();

    // PieceColor getColor();

    /**
     *  Minecraft Logic
     */

    void spawnAt(@NotNull Location location);

    void moveTo(@NotNull Location location);

    void remove();

}
