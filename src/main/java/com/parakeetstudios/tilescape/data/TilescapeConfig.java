package com.parakeetstudios.tilescape.data;

import org.bukkit.configuration.file.FileConfiguration;

public class TilescapeConfig {

    private final FileConfiguration cfg;

    public TilescapeConfig(FileConfiguration cfg) {
        this.cfg = cfg;
    }

    public String getDefaultChessStateNotation() {
        return cfg.getString("DEFAULT_CHESS_STATE_NOTATION");
    }

    public String getDefaultChessSetup() {
        return cfg.getString("DEFAULT_CHESS_SETUP");
    }

    public String getDefaultChessMoveNotation() {
        return cfg.getString("DEFAULT_CHESS_MOVE_NOTATION");
    }

    public String getChessModelType() { return cfg.getString("CHESS_MODEL_TYPE"); }

}
