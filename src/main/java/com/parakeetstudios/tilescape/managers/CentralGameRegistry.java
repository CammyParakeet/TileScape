package com.parakeetstudios.tilescape.managers;

import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.game.BoardGame;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Singleton
public class CentralGameRegistry {

    private final Set<GameManager> registeredManagers = new HashSet<>();
    // consider adjusting storing Player references instead of IDs here?
    private final Set<UUID> allGamePlayers = new HashSet<>();


    public void registerGameManager(GameManager manager) {
        registeredManagers.add(manager);
    }

    public void playerJoinedGame(UUID playerID) {
        allGamePlayers.add(playerID);
    }

    public void playerLeftGame(UUID playerID) {
        allGamePlayers.remove(playerID);
    }

    public boolean isPlayerInGame(UUID playerID) {
        return allGamePlayers.contains(playerID);
    }

    public Optional<? extends BoardGame> getPlayersGame(UUID playerID) {
        for (GameManager manager : registeredManagers) {
            Optional<? extends BoardGame> game = manager.getPlayersGame(playerID);
            if (game.isPresent()) return game;
        }
        return Optional.empty();
    }

    public Set<GameManager> getRegisteredManagers() {
        return registeredManagers;
    }


}
