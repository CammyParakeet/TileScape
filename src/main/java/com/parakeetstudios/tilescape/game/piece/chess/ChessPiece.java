package com.parakeetstudios.tilescape.game.piece.chess;

import com.parakeetstudios.tilescape.core.piece.PieceType;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import com.parakeetstudios.tilescape.game.piece.SimplePieceColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * @author Cammy
 * @version 1.0
 */

public class ChessPiece implements Piece {

    //private final PieceType type;
    private final PieceColor color;
    private Entity marker; // possibly needed for interaction?
    private Entity model;

    public ChessPiece(char type, PieceColor color) {
        this.color = color;
        //this.type = ?;
    }

    @Override
    public void spawnAt(@NotNull Location location) {
        //model = .... spawn a model based on type
    }

    @Override
    public void moveTo(@NotNull Location location) {
        //TODO more advanced move animation?
        model.teleport(location);
    }

    @Override
    public void remove() {
        model.remove();
    }
}
