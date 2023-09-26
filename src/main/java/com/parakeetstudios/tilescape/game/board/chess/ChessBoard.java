package com.parakeetstudios.tilescape.game.board.chess;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.BoardPosition;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.chess.ChessPiece;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Cammy
 * @version 1.0
 */

public class ChessBoard implements Board {

    private final TilescapeConfig cfg;

    private final int WIDTH = 8; //TODO allow config
    private final Location minecraftOrigin;
    private final Piece[][] pieces;

    @Inject
    public ChessBoard(@NotNull Location origin, @NotNull TilescapeConfig cfg) {
        this.minecraftOrigin = origin;
        this.pieces = new ChessPiece[WIDTH][WIDTH];
        this.cfg = cfg;
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
     * Board Setup
     */

    @Override
    public void build() {
        //TODO setup config
        switch (cfg.getDefaultChessStateNotation()) {
            case "FEN" -> buildFEN();
            case "other?" -> {}
        }
    }

    private void buildFEN() {
        String setup = cfg.getDefaultChessSetup();



    }

    private void setPiece(int file, int rank) {
        ChessPiece piece = new ChessPiece();
        pieces[file][rank] = piece;
        //piece.spawnAt();
    }


    /**
     *  Move management
     */

    @Override
    public boolean attemptMove(@NotNull BoardPosition from, @NotNull BoardPosition to) {
        ChessPiece pieceToMove = (ChessPiece) getPieceAt(from).orElse(null);
        ChessPiece pieceToTake = (ChessPiece) getPieceAt(to).orElse(null);

        //TODO move validation

        pieces[to.file()][to.rank()] = pieceToMove;
        pieces[from.file()][from.rank()] = null;

        //pieceToMove.moveTo();

        // Handle capturing
        if (pieceToTake != null) {
            //TODO handle
            return true;
        }

        return true;
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
