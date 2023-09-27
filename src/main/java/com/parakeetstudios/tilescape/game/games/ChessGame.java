package com.parakeetstudios.tilescape.game.games;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.inject.BoardFactory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ChessGame implements BoardGame {

    private final UUID gameID;
    private final Board board;

    @Inject
    public ChessGame(@NotNull List<UUID> players,
                     @NotNull Location gameLocation,
                     @NotNull BoardFactory boardFactory)
    {
        this.gameID = UUID.randomUUID();
        this.board = boardFactory.createBoard("chess", gameLocation);
    }

    @Override
    public void build() {
        board.build();
    }

    @Override
    public UUID getGameID() {
        return gameID;
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
