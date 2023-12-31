package com.parakeetstudios.tilescape.core.tasks;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.parakeetstudios.tilescape.core.managers.SelectionManager;
import com.parakeetstudios.tilescape.core.utils.BlockUtils;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HoverDisplayTask extends BukkitRunnable {

    private final UUID playerID;
    private final Player player;
    private final World playerWorld;
    private final BoardGame playersGame;
    private final SelectionManager selectionManager;
    private final double maxDistance;

    @AssistedInject
    public HoverDisplayTask(@Assisted @NotNull UUID playerID,
                            @Assisted @NotNull BoardGame playersGame,
                            @NotNull SelectionManager selectionManager,
                            @NotNull TilescapeConfig cfg
                            ) {
        this.playerID = playerID;
        this.player = Bukkit.getPlayer(playerID);
        if (this.player == null) {
            throw new IllegalArgumentException("No Player with UUID " + playerID + " found");
        }
        this.playerWorld = player.getWorld();
        this.selectionManager = selectionManager;
        // TODO some exception needed here
        this.playersGame = playersGame;
        this.maxDistance = cfg.getMaxRaytraceDistance();
    }

    @Override
    public void run() {
        // TODO put this into listeners instead of actual task?
        if (!playersGame.isPlayerTurn(playerID)) {
            cancel();
            return;
        }

         Block target = BlockUtils.getTargetedBlock(playerWorld, player, maxDistance);

        // this will happen if blocks are out of range - so we don't show a hover
        if (target == null) {
            selectionManager.clearHover(playerID);
            return;
        }
        // update to display the hover
        selectionManager.updateHover(playerID, target);
    }
}
