package com.parakeetstudios.tilescape.managers;

import com.parakeetstudios.tilescape.game.BoardGame;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * @author Cammy
 * @version 1.0
 */


/**
 * TODO
 */
public interface GameManager extends Manager {

    /**
     * Builds a new game session for two players identified by UUIDs.
     *
     * @param playersIDs the list of players to join the game
     */
    BoardGame buildGame(List<UUID> playersIDs);

    /**
     * Registers a new game session to the managers active games.
     * @param game The game to register.
     */
    void registerGame(@NotNull BoardGame game);

    /**
     * Checks if a player, using UUID, is in an active game session.
     *
     * @param playerID The unique ID of the player.
     * @return True if player in game, otherwise false.
     */
    boolean isPlayerInGame(@NotNull UUID playerID);

    /**
     * Ends the game session.
     * @param game The game to end.
     */
    void endGame(@NotNull BoardGame game);


}
