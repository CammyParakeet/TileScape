package com.parakeetstudios.tilescape.managers.games;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.utils.LocationUtils;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.games.ChessGame;
import com.parakeetstudios.tilescape.inject.GameFactory;
import com.parakeetstudios.tilescape.managers.CentralGameRegistry;
import com.parakeetstudios.tilescape.managers.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class ChessGameManager implements GameManager, Listener {

    private final TilescapePlugin plugin;
    private final TilescapeConfig cfg;
    private final CentralGameRegistry gameRegistry;
    private final GameFactory<ChessGame> gameFactory;

    private final Map<UUID, ChessGame> activeChessGames = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> playersInChessGames = new ConcurrentHashMap<>();
    private final List<Location> chessLocations;


    @Inject
    public ChessGameManager(@NotNull TilescapePlugin plugin,
                            @NotNull TilescapeConfig cfg,
                            @NotNull CentralGameRegistry gameRegistry,
                            @NotNull GameFactory<ChessGame> gameFactory
                            )
    {
        this.cfg = cfg;
        this.plugin = plugin;
        this.gameRegistry = gameRegistry;
        this.gameFactory = gameFactory;
        this.chessLocations = cfg.getChessLocations();

        this.gameRegistry.registerGameManager(this);
    }

    @Override
    public BoardGame buildGame(List<UUID> playerIDs) {
        // return a created game
        return gameFactory.create(playerIDs, LocationUtils.giveRandomGameLocation(chessLocations));
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {
        activeChessGames.put(game.getGameID(), (ChessGame) game);
    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID playerID) {
        return playersInChessGames.containsKey(playerID);
    }


    public Optional<BoardGame> getPlayersGame(UUID pid) {
        if (!playersInChessGames.containsKey(pid)) return Optional.empty();

        UUID gameID = playersInChessGames.get(pid);
        return Optional.ofNullable(activeChessGames.get(gameID));
    }

    @Override
    public Set<UUID> getAllGamePlayers() {
        return playersInChessGames.keySet();
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
        activeChessGames.values().forEach(ChessGame::destroy);
        this.activeChessGames.clear();
    }
}
