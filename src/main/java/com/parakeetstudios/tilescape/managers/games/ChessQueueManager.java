package com.parakeetstudios.tilescape.managers.games;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.managers.QueueManager;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ChessQueueManager extends QueueManager {

    @Inject
    public ChessQueueManager(@NotNull TilescapePlugin plugin,
                             @NotNull TilescapeConfig cfg,
                             @NotNull ChessGameManager gameManager) {
        super(cfg.getDefaultMaxGamePlayers("Chess"), plugin, cfg, gameManager);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {

    }
}
