package com.parakeetstudios.tilescape.managers.chess;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.utils.LocationUtils;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.games.ChessGame;
import com.parakeetstudios.tilescape.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ChessGameManager implements GameManager, Listener {

    private final TilescapePlugin plugin;
    private final TilescapeConfig cfg;
    private final Map<UUID, ChessGame> activeChessGames = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> playerInGame = new ConcurrentHashMap<>();
    private final List<Location> chessLocations;
    //TODO get game locations from config?

    @Inject
    public ChessGameManager(@NotNull TilescapePlugin plugin,
                            @NotNull TilescapeConfig cfg
                            )
    {
        this.cfg = cfg;
        this.plugin = plugin;
        this.chessLocations = cfg.getChessLocations();
    }

    @Override
    public void buildGame(List<UUID> playerIDs) {
        Location location = LocationUtils.giveRandomGameLocation(chessLocations);

        //ChessGame game = new ChessGame(playerIDs, location);
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {
        activeChessGames.put(game.getGameID(), (ChessGame) game);
    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID playerID) {
        return playerInGame.containsKey(playerID);
    }

    @Override
    public void endGame(@NotNull BoardGame game) {
        activeChessGames.remove(game.getGameID());
        game.destroy();
        //TODO events?
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {

    }
}
