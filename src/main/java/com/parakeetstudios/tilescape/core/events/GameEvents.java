package com.parakeetstudios.tilescape.core.events;

import com.parakeetstudios.tilescape.game.BoardGame;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GameEvents {


    /**
     * Called when a player joins a game
     */
    public static class PlayerEnterGameEvent extends GameEvent {

        public PlayerEnterGameEvent(UUID playerID, BoardGame game) {
            super(playerID, game);
        }
    }


    /**
     * Called when a player leaves a game
     */
    public static class PlayerLeaveGameEvent extends GameEvent {

        public PlayerLeaveGameEvent(UUID playerID, BoardGame game) {
            super(playerID, game);
        }
    }


}
