package com.parakeetstudios.tilescape.managers;

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
    
    void onEnable();

    void buildGame(@NotNull UUID p1, @NotNull UUID p2);

    void registerGame();

    boolean isPlayerInGame(UUID pID);

    void endGame();

    void onDisable();

}
