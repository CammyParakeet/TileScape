package com.parakeetstudios.tilescape.core.piece;

import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

public interface PieceType {

    char getSymbol();
    Entity spawnModel(Location location, PieceColor color);
    Vector3f getScale();

}
