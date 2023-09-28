package com.parakeetstudios.tilescape.game;

import com.parakeetstudios.tilescape.game.board.Board;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * @author Cammy
 * @version 1.0
 */


/**
 * Represents a board-game session.
 */
public interface BoardGame {

    /**
     * Initializes and sets up session.
     */
    void build();

    /**
     * Get the UUID of this game session.
     *
     * @return a UUID.
     */
    UUID getGameID();

    /**
     * Gets the set of players participating in this game.
     *
     * @return A set of UUIDs representing the players.
     */
    List<UUID> getPlayers();

    /**
     * Gets the set of players spectating this game.
     *
     * @return A set of UUIDs representing the players.
     */
    List<UUID> getViewers();

    /**
     * Gets the game board associated with this session.
     *
     * @return The game board.
     */
    Board getBoard();

    /**
     * Checks if it's the players turn, using their UUID.
     *
     * @param playerID The UUID of the player.
     * @return True if it's the player's turn, otherwise false.
     */
    boolean isPlayerTurn(@NotNull UUID playerID);

    /**
     * Ends the current player's turn and passes control to the next player.
     */
    void endTurn();

    /**
     * Cleans up and terminates the game session.
     */
    void destroy();


}
