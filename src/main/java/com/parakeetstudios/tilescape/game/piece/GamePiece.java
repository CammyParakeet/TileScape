package com.parakeetstudios.tilescape.game.piece;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.core.piece.PieceController;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.inject.PieceControllerFactory;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class GamePiece implements Piece {

    protected final TilescapeConfig cfg;
    protected final PieceControllerFactory pieceTypeFactory;

    protected final char symbol;
    protected final PieceColor color;
    protected PieceController controller;
    protected Entity marker; // possibly needed for interaction?
    protected Entity model;

    @Inject
    public GamePiece(char symbol,
                     PieceColor color,
                     TilescapeConfig cfg,
                     PieceControllerFactory pieceTypeFactory)
    {
        this.symbol = symbol;
        this.color = color;
        this.cfg = cfg;
        this.pieceTypeFactory = pieceTypeFactory;
    }

    protected void determineType(String gameModelType) {
        this.controller = pieceTypeFactory.createType(gameModelType, symbol, color);
    }

    @Override
    public void spawnAt(@NotNull Location location) {
        model = controller.spawnModel(location, color);
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
