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

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {

    }
}
