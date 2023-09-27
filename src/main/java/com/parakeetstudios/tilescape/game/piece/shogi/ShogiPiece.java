package com.parakeetstudios.tilescape.game.piece.shogi;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.GamePiece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import com.parakeetstudios.tilescape.inject.PieceSpawnerFactory;
import org.jetbrains.annotations.NotNull;

public class ShogiPiece extends GamePiece {

    @Inject
    public ShogiPiece(@Assisted char symbol,
                      @Assisted @NotNull PieceColor color,
                      @NotNull TilescapeConfig cfg)
    {
        super(symbol, color, cfg);
        String gameModelType = cfg.getShogiModelType() + "_shogi";
        //determineType(gameModelType);
    }
}
