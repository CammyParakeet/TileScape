package com.parakeetstudios.tilescape.game.piece.chess;

import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.GamePiece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import com.parakeetstudios.tilescape.inject.PieceControllerFactory;

/**
 * @author Cammy
 * @version 1.0
 */

// POSSIBLY UNUSED?

public class ChessPiece extends GamePiece {


    public ChessPiece(char symbol,
                      PieceColor color,
                      TilescapeConfig cfg,
                      PieceControllerFactory pieceTypeFactory)
    {
        super(symbol, color, cfg, pieceTypeFactory);
        String gameModelType = cfg.getChessModelType() + "_chess";
        determineType(gameModelType);
    }
}
