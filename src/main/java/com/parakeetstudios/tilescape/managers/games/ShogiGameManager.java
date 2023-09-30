package com.parakeetstudios.tilescape.managers.games;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.games.ChessGame;
import com.parakeetstudios.tilescape.game.games.ShogiGame;
import com.parakeetstudios.tilescape.inject.GameFactory;
import com.parakeetstudios.tilescape.managers.CentralGameRegistry;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ShogiGameManager implements GameManager {


    private final TilescapePlugin plugin;
    private final TilescapeConfig cfg;
    private final CentralGameRegistry gameRegistry;
    private final GameFactory<ShogiGame> gameFactory;

    private final Map<UUID, ShogiGame> activeShogiGames = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> playersInShogiGames = new ConcurrentHashMap<>();
    private final List<Location> shogiLocations;


    @Inject
    public ShogiGameManager(@NotNull TilescapePlugin plugin,
                            @NotNull TilescapeConfig cfg,
                            @NotNull CentralGameRegistry gameRegistry,
                            @NotNull GameFactory<ShogiGame> gameFactory
    )
    {
        this.cfg = cfg;
        this.plugin = plugin;
        this.gameRegistry = gameRegistry;
        this.gameFactory = gameFactory;
        this.shogiLocations = cfg.getChessLocations(); // fix

        this.gameRegistry.registerGameManager(this);
    }


    @Override
    public BoardGame buildGame(List<UUID> playersIDs) {
        return null;
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {

    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID playerID) {
        return false;
    }

    @Override
    public Optional<BoardGame> getPlayersGame(UUID pid) {
        return Optional.empty();
    }

    @Override
    public Set<UUID> getAllGamePlayers() {
        return null;
    }

    @Override
    public void endGame(@NotNull BoardGame game) {

    }

    @Override
    public void onEnable() {
        Paralog.info("Shogi starting?");
    }

    @Override
    public void onDisable() {

    }
}
