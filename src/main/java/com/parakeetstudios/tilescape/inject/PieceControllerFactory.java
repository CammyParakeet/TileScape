package com.parakeetstudios.tilescape.inject;

import com.google.inject.name.Named;
import com.parakeetstudios.tilescape.core.piece.PieceController;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

public interface PieceControllerFactory {
    PieceController createType(@NotNull @Named("type") String type, char symbol, @NotNull PieceColor color);
}
