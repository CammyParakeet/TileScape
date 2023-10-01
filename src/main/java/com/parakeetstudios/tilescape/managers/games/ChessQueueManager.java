package com.parakeetstudios.tilescape.managers.games;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.managers.CentralQueueRegistry;
import com.parakeetstudios.tilescape.managers.QueueManager;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ChessQueueManager extends QueueManager {

    @Inject
    public ChessQueueManager(@NotNull TilescapePlugin plugin,
                             @NotNull TilescapeConfig cfg,
                             @NotNull ChessGameManager gameManager,
                             @NotNull CentralQueueRegistry queueRegistry) {
        super(cfg.getDefaultMaxGamePlayers("Chess"), "Chess", plugin, cfg, gameManager, queueRegistry);
    }

    @Override
    public void onEnable() {
        //if (!cfg.isGameEnabled(this)) return; TODO

        Bukkit.getPluginManager().registerEvents(this, plugin);
        super.isActive = true;

        // enable corresponding manager
        super.gameManager.onEnable();
    }

    @Override
    public void onDisable() {
        super.stop();
        super.gameManager.onDisable();
    }
}
