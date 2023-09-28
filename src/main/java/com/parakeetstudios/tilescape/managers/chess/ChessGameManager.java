package com.parakeetstudios.tilescape.managers.chess;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;
import com.parakeetstudios.tilescape.core.utils.LocationUtils;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.games.ChessGame;
import com.parakeetstudios.tilescape.inject.GameFactory;
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
    private final Map<UUID, ChessGame> activeChessGames = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> playerInGame = new ConcurrentHashMap<>();
    private final List<Location> chessLocations;
    private final GameFactory<ChessGame> gameFactory;

    @Inject
    public ChessGameManager(@NotNull TilescapePlugin plugin,
                            @NotNull TilescapeConfig cfg,
                            @NotNull GameFactory<ChessGame> gameFactory
                            )
    {
        this.cfg = cfg;
        this.plugin = plugin;
        this.chessLocations = cfg.getChessLocations();
        this.gameFactory = gameFactory;
    }

    @Override
    public BoardGame buildGame(List<UUID> playerIDs) {
        return gameFactory.create(playerIDs, LocationUtils.giveRandomGameLocation(chessLocations));
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {
        activeChessGames.put(game.getGameID(), (ChessGame) game);
    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID playerID) {
        return playerInGame.containsKey(playerID);
    }


    public Optional<BoardGame> getPlayersGame(UUID pid) {
        if (!playerInGame.containsKey(pid)) return Optional.empty();

        UUID gameID = playerInGame.get(pid);
        return Optional.ofNullable(activeChessGames.get(gameID));
    }

    @Override
    public List<UUID> getAllGamePlayers() {
        return new ArrayList<>(playerInGame.keySet());
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
        //new HoverDisplayTask(plugin).runTaskTimer(plugin, 0, 2); TODO needs bound in module
    }

    @Override
    public void onDisable() {
        activeChessGames.values().forEach(ChessGame::destroy);
        this.activeChessGames.clear();
    }
}
