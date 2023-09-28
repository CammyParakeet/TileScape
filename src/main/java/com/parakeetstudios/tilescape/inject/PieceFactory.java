package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

public interface PieceFactory<P extends Piece> {
    P createPiece(char symbol, @NotNull PieceColor color, @NotNull RendererFactory rendererFactory);
}

