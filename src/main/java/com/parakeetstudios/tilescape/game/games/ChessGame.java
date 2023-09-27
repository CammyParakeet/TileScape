package com.parakeetstudios.tilescape.game.games;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.inject.BoardFactory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.UUID;

public class ChessGame implements BoardGame {

    @Inject
    private BoardFactory boardFactory;


    public ChessGame(@NotNull Location gameLocation, BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
        Board board = this.boardFactory.createBoard("chess", gameLocation);
    }

    @Override
    public void build() {

    }

    @Override
    public UUID getGameID() {
        return null;
    }

    @Override
    public Set<UUID> getPlayers() {
        return null;
    }

    @Override
    public Set<UUID> getViewers() {
        return null;
    }

    @Override
    public boolean isPlayerTurn(@NotNull UUID pID) {
        return false;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public void destroy() {

    }
}
