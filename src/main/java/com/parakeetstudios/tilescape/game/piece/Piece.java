package com.parakeetstudios.tilescape.game.piece;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * @author Cammy
 * @version 1.0
 */

public interface Piece {

    /**
     * Gives the character symbol representing the piece.
     *
     * @return symbol of the piece.
     */
    char getSymbol();

    /**
     * Gives the color of the piece.
     *
     * @return color of the piece.
     */
    PieceColor getColor();

    /**
     *  Minecraft Logic
     */

    /**
     * Spawns the piece at the specified location.
     *
     * @param location The {@link Location} where the piece model should be spawned.
     */
    void spawnAt(@NotNull Location location);

    /**
     * Moves the piece to the specified location.
     *
     * @param location The target {@link Location} where the piece model should be moved to.
     */
    void moveTo(@NotNull Location location);

    /**
     * Despawns the piece model
     */
    void remove();

}
