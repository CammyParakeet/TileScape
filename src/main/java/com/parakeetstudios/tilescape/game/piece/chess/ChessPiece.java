package com.parakeetstudios.tilescape.game.piece.chess;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.parakeetstudios.tilescape.core.utils.PieceRenderer;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.GamePiece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

/**
 * @author Cammy
 * @version 1.0
 */

public class ChessPiece extends GamePiece {

    @AssistedInject
    public ChessPiece(@Assisted char symbol,
                      @Assisted @NotNull PieceColor color,
                      @NotNull PieceRenderer renderer,
                      @NotNull TilescapeConfig cfg)
    {
        super(symbol, color, renderer, cfg);
    }
}
