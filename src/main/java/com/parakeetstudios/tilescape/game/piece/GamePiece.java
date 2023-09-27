package com.parakeetstudios.tilescape.game.piece;

import com.parakeetstudios.tilescape.core.piece.PieceSpawner;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.inject.PieceSpawnerFactory;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class GamePiece implements Piece {

    protected final TilescapeConfig cfg;
    //protected final PieceSpawnerFactory pieceTypeFactory;

    protected final char symbol;
    protected final PieceColor color;
    protected PieceSpawner controller;
    protected Entity marker; // possibly needed for interaction?
    protected Entity model;

    public GamePiece(char symbol,
                     @NotNull PieceColor color,
                     @NotNull TilescapeConfig cfg)
    {
        this.symbol = symbol;
        this.color = color;
        this.cfg = cfg;
        //this.pieceTypeFactory = pieceTypeFactory;
    }

    protected void determineType(String gameModelType) {
        //this.controller = pieceTypeFactory.createSpawner(symbol, color);
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
