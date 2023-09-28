package com.parakeetstudios.tilescape.game.piece;

import com.parakeetstudios.tilescape.core.utils.PieceRenderer;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * @author Cammy
 * @version 1.0
 */

public abstract class GamePiece implements Piece {

    protected final TilescapeConfig cfg;
    protected final PieceRenderer renderer;

    protected final char symbol;
    protected final PieceColor color;

    // protected Entity marker; - possibly needed for interaction?
    protected Entity model;

    public GamePiece(char symbol,
                     PieceColor color,
                     PieceRenderer renderer,
                     TilescapeConfig cfg)
    {
        this.symbol = symbol;
        this.color = color;
        this.cfg = cfg;
        this.renderer = renderer;
    }


    @Override
    public char getSymbol() { return symbol; }

    @Override
    public PieceColor getColor() { return color; }

    @Override
    public void spawnAt(@NotNull Location location) {
        model = renderer.renderPiece(this, location);
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
