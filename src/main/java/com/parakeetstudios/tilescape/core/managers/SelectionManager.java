package com.parakeetstudios.tilescape.core.managers;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.selector.BoardSelection;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.managers.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SelectionManager implements Manager, Listener {

    private final TilescapePlugin plugin;
    private final TilescapeConfig cfg;
    private final GameManager gameManager;

    private final Map<UUID, Block> lastBlockHovered = new ConcurrentHashMap<>();
    private final Map<UUID, BlockDisplay> currentHovers = new ConcurrentHashMap<>();
    private final Map<UUID, BoardSelection> currentSelections = new ConcurrentHashMap<>();

    //TODO collect selection colors from config
    private final Color DEFAULT = Color.WHITE;
    private final Color SELECTED = Color.fromRGB(131, 220, 252);
    private final Color SUCCESS = Color.fromRGB(41, 221, 31);
    private final Color ERROR = Color.RED;
    private final Color NULL = Color.fromRGB(126, 126, 126);

    @Inject
    public SelectionManager(@NotNull TilescapePlugin plugin,
                            @NotNull GameManager gameManager,
                            @NotNull TilescapeConfig cfg)
    {
        this.plugin = plugin;
        this.cfg = cfg;
        this.gameManager = gameManager;
    }

    // clears the players hover display
    public void clearHover(UUID playerID) {
        if (!currentHovers.containsKey(playerID)) return;

        currentHovers.get(playerID).remove();
        currentHovers.remove(playerID);
        lastBlockHovered.remove(playerID);
    }


    public void updateHover(UUID playerID, Block block) {
        // return if we haven't changed blocks
        if (block.equals(lastBlockHovered.get(playerID))) return;

        clearHover(playerID);

        //currentHovers.put(playerID, some spawn method for selections)
        lastBlockHovered.put(playerID, block);
    }


    public void clearSelection(UUID playerID) {
        if (currentSelections.containsKey(playerID)) {
            currentSelections.get(playerID).remove();
        }
    }

    public void updateSelection(UUID playerID, Color color) {
        // check if it has a piece
        Block currentBlock = lastBlockHovered.get(playerID);
        // some method to get the piece

        clearSelection(playerID);

        //BoardSelection selection = new BoardSelection(currentBlock, color, method to get piece?)
        //currentSelections.put(playerID, selection);
    }



    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {

    }
}
