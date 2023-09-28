package com.parakeetstudios.tilescape.game.games;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.chess.ChessBoard;
import com.parakeetstudios.tilescape.inject.BoardFactory;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class ChessGame implements BoardGame {

    private final UUID gameID;
    private final Board board;
    private final List<UUID> players;
    private final TilescapeConfig cfg;

    @AssistedInject
    public ChessGame(@Assisted @NotNull List<UUID> players,
                     @Assisted @NotNull Location gameLocation,
                     @NotNull TilescapeConfig cfg,
                     @NotNull BoardFactory<ChessBoard> boardFactory)
    {
        this.players = players;
        this.cfg = cfg;
        this.gameID = UUID.randomUUID();
        this.board = boardFactory.createBoard(gameLocation);
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
    public List<UUID> getPlayers() {
        return players;
    }

    @Override
    public List<UUID> getViewers() {
        return null;
    }

    @Override
    public boolean isPlayerTurn(@NotNull UUID playerID) {
        return false;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public void destroy() {

    }
}
