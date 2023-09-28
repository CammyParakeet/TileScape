package com.parakeetstudios.tilescape.game.piece.shogi;

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

// TEMP CLASS
public class ShogiPiece extends GamePiece {

    @AssistedInject
    public ShogiPiece(@Assisted char symbol,
                      @Assisted @NotNull PieceColor color,
                      @Assisted @NotNull PieceRenderer renderer,
                      @NotNull TilescapeConfig cfg)
    {
        super(symbol, color, renderer, cfg);
    }
}
