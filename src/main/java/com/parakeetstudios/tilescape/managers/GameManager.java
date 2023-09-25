package com.parakeetstudios.tilescape.managers;

import com.parakeetstudios.tilescape.game.BoardGame;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author Cammy
 * @version 1.0
 */


/**
 * TODO
 */
public interface GameManager {

    /**
     * Initializes game manager
     */
    void onEnable();

    /**
     * Builds a new game session for two players identified by UUIDs.
     *
     * @param pID1 The unique ID of the first player.
     * @param pID2 The unique ID of the second player.
     */
    void buildGame(@NotNull UUID pID1, @NotNull UUID pID2);

    /**
     * Registers a new game session to the managers active games.
     * @param game The game to register.
     */
    void registerGame(@NotNull BoardGame game);

    /**
     * Checks if a player, using UUID, is in an active game session.
     *
     * @param pID The unique ID of the player.
     * @return True if player in game, otherwise false.
     */
    boolean isPlayerInGame(UUID pID);

    /**
     * Ends the game session.
     * @param game The game to end.
     */
    void endGame(@NotNull BoardGame game);

    /**
     * Deactivates game manager
     */
    void onDisable();

}
