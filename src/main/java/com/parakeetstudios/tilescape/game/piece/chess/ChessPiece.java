package com.parakeetstudios.tilescape.game.piece.chess;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.GamePiece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.jetbrains.annotations.NotNull;

/**
 * @author Cammy
 * @version 1.0
 */

public class ChessPiece extends GamePiece {

    @Inject
    public ChessPiece(@Assisted char symbol,
                      @Assisted @NotNull PieceColor color,
                      @NotNull TilescapeConfig cfg)
    {
        super(symbol, color, cfg);
        String gameModelType = cfg.getChessModelType() + "_chess";
        //determineType(gameModelType);
    }
}
