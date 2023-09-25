package com.parakeetstudios.tilescape.game.board;

import org.bukkit.Location;

/**
 * @author Cammy
 * @version 1.0
 */

/**
 * Represents the board of a game, managing its layout, pieces, and game-related actions.
 */
public interface Board {

    /**
     * Initializes and sets up the game board.
     */
    void build();

    /**
     * Gets the origin point of the board in the Minecraft world.
     *
     * @return The Location of the origin point.
     */
    Location getMinecraftOrigin();

    /**
     * Retrieves the side-length size of the game board.
     *
     * @return The side-length size of the board.
     */
    int getSize();

    /**
     * Attempts to make a move on the board. Implementation will determine the move logic.
     *
     * @return True if the move was successful, otherwise false.
     */
    boolean attemptMove();

    // piece methods

    /**
     * Resets the board to initial state.
     */
    void reset();

    /**
     * Cleans up the board and releases associations.
     */
    void destroy();

}
