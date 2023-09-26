package com.parakeetstudios.tilescape.game.board;

import com.parakeetstudios.tilescape.game.piece.Piece;
import lombok.Getter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Cammy
 * @version 1.0
 */

/**
 * Represents the board of a game, managing its layout, pieces, and game-related actions.
 */
public interface Board {

    /**
     * Initializes and sets up the game board.
     */
    void build();

    /**
     * Gets the origin point of the board in the Minecraft world.
     *
     * @return The {@link Location} of the origin point.
     */
    Location getMinecraftOrigin();

    /**
     * Gets the side-length size of the game board.
     *
     * @return The side-length size of the board.
     */
    int getSize();

    /**
     * Attempts to make a move on the board. Implementation will determine the move logic.
     *
     * @return True if the move was successful, otherwise false.
     */
    boolean attemptMove(BoardPosition from, BoardPosition to);

    /**
     * Tries to get the piece located at the specific {@link BoardPosition}.
     *
     * @param pos The position on the board to check for a piece.
     * @return An optional for a {@link Piece}.
     */
    Optional<Piece> getPieceAt(@NotNull BoardPosition pos);

    /**
     * Performs the given action for each piece on the board.
     *
     * @param action The action to be performed on each {@link Piece}.
     */
    void forEachPiece(Consumer<Piece> action);


    /**
     * Resets the board to initial state.
     */
    void reset();

    /**
     * Cleans up the board and releases associations.
     */
    void destroy();

    /**
     * Represents directions on the tile board.
     * Each direction is associated with changes in rank and file coordinates.
     */
    enum Direction {
        LEFT_FILE(-1, 0),
        RIGHT_FILE(1, 0),
        UP_RANK(0, 1),
        DOWN_RANK(0, -1),
        UP_RIGHT(1, 1),
        UP_LEFT(1, -1),
        DOWN_RIGHT(-1, 1),
        DOWN_LEFT(-1, -1);

        @Getter
        private final int deltaFile, deltaRank;

        Direction(int dFile, int dRank) {
            this.deltaFile = dFile;
            this.deltaRank = dRank;
        }

    }

}
