package com.parakeetstudios.tilescape.managers;

import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class QueueManager implements UtilityManager, Listener {

    protected final TilescapePlugin plugin;
    protected final TilescapeConfig cfg;
    protected final GameManager gameManager;

    protected final Queue<UUID> waitingPlayers = new LinkedList<>();
    protected final int MAX_PLAYERS;


    public QueueManager(int MAX_PLAYERS,
                        @NotNull TilescapePlugin plugin,
                        @NotNull TilescapeConfig cfg,
                        @NotNull GameManager gameManager) {
        this.plugin = plugin;
        this.cfg = cfg;
        this.gameManager = gameManager;
        this.MAX_PLAYERS = MAX_PLAYERS;
    }


    public void addPlayerToQueue(UUID playerID) {
        waitingPlayers.add(playerID);
    }


    public void removePlayerFromQueue(UUID playerID) { waitingPlayers.remove(playerID); }


    public void checkForMatch() {
        if (waitingPlayers.size() >= MAX_PLAYERS) {
            List<UUID> players = new ArrayList<>(waitingPlayers);
            gameManager.buildGame(players);
        }
    }

}
