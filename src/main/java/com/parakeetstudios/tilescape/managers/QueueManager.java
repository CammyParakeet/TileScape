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

    protected final String queueName;
    protected final Queue<UUID> waitingPlayers = new LinkedList<>();
    protected final int MAX_PLAYERS;
    protected boolean isActive;


    public QueueManager(int MAX_PLAYERS,
                        String queueName,
                        @NotNull TilescapePlugin plugin,
                        @NotNull TilescapeConfig cfg,
                        @NotNull GameManager gameManager,
                        @NotNull CentralQueueRegistry queueRegistry) {
        this.plugin = plugin;
        this.cfg = cfg;
        this.gameManager = gameManager;
        this.MAX_PLAYERS = MAX_PLAYERS;
        this.queueName = queueName;
        queueRegistry.registerQueue(this);
    }

    public void stop() {
        waitingPlayers.clear();
        isActive = false;
    }

    public void addPlayerToQueue(UUID playerID) {
        waitingPlayers.add(playerID);
        checkForMatch();
    }


    public void removePlayerFromQueue(UUID playerID) { waitingPlayers.remove(playerID); }


    public void checkForMatch() {
        if (waitingPlayers.size() >= MAX_PLAYERS) {
            List<UUID> players = new ArrayList<>(waitingPlayers);
            gameManager.buildGame(players);
        }
    }

    @Override
    public String toString() {
        return queueName.toLowerCase();
    }
}
