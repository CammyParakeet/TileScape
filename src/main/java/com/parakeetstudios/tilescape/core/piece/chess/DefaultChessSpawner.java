package com.parakeetstudios.tilescape.core.piece.chess;

import com.parakeetstudios.tilescape.core.piece.PieceSpawner;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.joml.Vector3f;

public class DefaultChessSpawner implements PieceSpawner {

    @Override
    public char getSymbol() {
        return 0;
    }

    @Override
    public Entity spawnModel(Location location, PieceColor color) {
        return null;
    }

    @Override
    public Vector3f getScale() {
        return null;
    }
}
