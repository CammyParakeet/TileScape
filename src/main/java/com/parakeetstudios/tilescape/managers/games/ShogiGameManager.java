package com.parakeetstudios.tilescape.managers.games;

import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ShogiGameManager implements GameManager {

    @Override
    public BoardGame buildGame(List<UUID> playersIDs) {
        return null;
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {

    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID playerID) {
        return false;
    }

    @Override
    public Optional<BoardGame> getPlayersGame(UUID pid) {
        return Optional.empty();
    }

    @Override
    public List<UUID> getAllGamePlayers() {
        return null;
    }

    @Override
    public void endGame(@NotNull BoardGame game) {

    }

    @Override
    public void onEnable() {
        Paralog.info("Shogi starting?");
    }

    @Override
    public void onDisable() {

    }
}
