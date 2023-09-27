package com.parakeetstudios.tilescape.core.piece;

import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.joml.Vector3f;

public interface PieceSpawner {

    char getSymbol();
    Entity spawnModel(Location location, PieceColor color);
    Vector3f getScale();

}
