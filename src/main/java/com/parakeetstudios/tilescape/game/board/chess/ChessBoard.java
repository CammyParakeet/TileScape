package com.parakeetstudios.tilescape.game.board.chess;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.BoardPosition;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import com.parakeetstudios.tilescape.game.piece.SimplePieceColor;
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
        String fen = cfg.getDefaultChessSetup();

        int rank = WIDTH;
        int file = 1;

        for (char c : fen.toCharArray()) {
            if (Character.isDigit(c)) {
                file += Character.getNumericValue(c); // skip # of files
            } else if (c == '/') {
                rank --;  // lower the rank
                file = 1; // reset the file
            } else {
                // this means it's a piece
                setPiece(c, file, rank);
                file++;
            }
        }
    }

    private void setPiece(char type, int file, int rank) {
        // determine whether white or black
        PieceColor color = Character.isUpperCase(type) ? SimplePieceColor.WHITE : SimplePieceColor.BLACK;

        ChessPiece piece = new ChessPiece(type, color);
        pieces[file][rank] = piece;
        //piece.spawnAt(); TODO method to convert boardpos to mc location
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
