package com.parakeetstudios.tilescape.utils;

import com.parakeetstudios.tilescape.core.piece.PieceSpawner;
import com.parakeetstudios.tilescape.core.piece.chess.DebugChessSpawner;
import com.parakeetstudios.tilescape.core.piece.chess.DefaultChessSpawner;

import java.util.Map;

public class Controllers {

    private static final Map<String, Class<? extends PieceSpawner>> CONTROLLER_MAPPING = Map.of(
            "chess_debug", DebugChessSpawner.class,
            "chess_default", DefaultChessSpawner.class
    );

    public static Class<? extends PieceSpawner> getControllerClass(String key) {
        return CONTROLLER_MAPPING.get(key);
    }

}
