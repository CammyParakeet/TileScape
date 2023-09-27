package com.parakeetstudios.tilescape.inject;

import com.google.inject.name.Named;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

public interface PieceFactory {
    Piece createPiece(char symbol, @NotNull PieceColor color);
}

