package com.parakeetstudios.tilescape.inject;

import com.google.inject.name.Named;
import com.parakeetstudios.tilescape.core.piece.PieceSpawner;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

public interface PieceSpawnerFactory<S extends PieceSpawner> {
    S createSpawner(char symbol, @NotNull PieceColor color);
}
