package com.parakeetstudios.tilescape.core.tasks;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.managers.SelectionManager;
import com.parakeetstudios.tilescape.core.utils.BlockUtils;
import com.parakeetstudios.tilescape.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HoverDisplayTask extends BukkitRunnable {

    private final GameManager gameManager;
    private final SelectionManager selectionManager;

    @Inject
    public HoverDisplayTask(@NotNull SelectionManager selectionManager,
                            @NotNull GameManager gameManager
                            ) {
        this.selectionManager = selectionManager;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        for (UUID playerID : gameManager.getAllGamePlayers()) {
            Player p = Bukkit.getPlayer(playerID);
            if (p == null) continue;

            Block block = BlockUtils.getTargetedBlock(p, 7.5);

            if (block == null) {
                selectionManager.clearHover(playerID);
                continue;
            }
            selectionManager.updateHover(playerID, block);
        }

    }
}
