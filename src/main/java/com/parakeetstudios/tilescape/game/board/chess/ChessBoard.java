package com.parakeetstudios.tilescape.game.board.chess;

import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.BoardPosition;
import com.parakeetstudios.tilescape.game.piece.Piece;
import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public class ChessBoard implements Board {

    private final int WIDTH = 8; //TODO allow config
    private final Location minecraftOrigin;
    private final Piece[][] pieces;

    public ChessBoard(Location origin) {
        this.minecraftOrigin = origin;
        this.pieces = new Piece[WIDTH][WIDTH];
    }

    /**
     * Board Setup
     */

    @Override
    public void build() {

    }

    @Override
    public Location getMinecraftOrigin() {
        return this.minecraftOrigin;
    }

    @Override
    public int getSize() {
        return WIDTH;
    }

    /**
     *  Move management
     */

    @Override
    public boolean attemptMove() {
        return false;
    }


    /**
     * Piece management
     */

    @Override
    public Optional<Piece> getPieceAt(@NotNull BoardPosition pos) {
        return Optional.empty();
    }

    @Override
    public void forEachPiece(Consumer<Piece> action) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String toString() {
        //TODO need custom board representation for debugging

        return super.toString();
    }
}
