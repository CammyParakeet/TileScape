package com.parakeetstudios.tilescape.core.managers;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.events.GameEvents;
import com.parakeetstudios.tilescape.core.selector.BoardSelection;
import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.inject.TaskFactory;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.managers.UtilityManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SelectionManager implements UtilityManager, Listener {

    private final TilescapePlugin plugin;
    private final TilescapeConfig cfg;
    private final GameManager gameManager;
    private final TaskFactory taskFactory;

    private final Map<UUID, HoverDisplayTask> activeHoverTasks = new ConcurrentHashMap<>();
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
                            @NotNull TaskFactory taskFactory,
                            @NotNull TilescapeConfig cfg)
    {
        this.plugin = plugin;
        this.cfg = cfg;
        this.gameManager = gameManager;
        this.taskFactory = taskFactory;
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

        //currentHovers.put(playerID, TODO some spawn method for selections)
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
        // TODO some method to get the piece

        clearSelection(playerID);

        //BoardSelection selection = new BoardSelection(currentBlock, color, TODO method to get piece?)
        //currentSelections.put(playerID, selection);
    }

    private Optional<Piece> getSelectedPiece(UUID playerID, Block selected) {
        Board board = gameManager.getPlayersGame(playerID).orElseThrow().getBoard();
        //return board.getPieceAt(); TODO method to get boardselection of a block?
        return null;
    }


    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();

        // if they have no hovers - they aren't in game so return
        if (!currentHovers.containsKey(playerID)) return;

        BoardGame game = gameManager.getPlayersGame(playerID).orElse(null);
        // maybe later allow this with pre-moving?
        if (game == null || !game.isPlayerTurn(playerID)) return;

        Action action = event.getAction();
        switch (action) {
            case LEFT_CLICK_BLOCK, LEFT_CLICK_AIR -> updateSelection(playerID, SELECTED);
            case RIGHT_CLICK_BLOCK, RIGHT_CLICK_AIR -> {
                // right click is called twice (main hand and offhand)
                if (event.getHand() != EquipmentSlot.HAND) return;
                // if player hasn't selected anything yet
                if (!currentSelections.containsKey(playerID)) return;

                //TODO handleMoveAttempt(pid, game);
            }
        }
    }


    @EventHandler
    public void onPlayerEnterGame(GameEvents.PlayerEnterGameEvent event) {
        UUID playerID = event.getPlayerID();
        HoverDisplayTask hoverTask = taskFactory.createHoverTask(playerID);
        hoverTask.runTaskTimer(plugin, 0, 2);
        activeHoverTasks.put(playerID, hoverTask);
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();
        clearHover(playerID);
        clearSelection(playerID);
        //TODO other storage logic with player who left?
    }

    @Override
    public void onEnable() {
        Paralog.info("Selections starting?");
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {
        currentHovers.values().forEach(Entity::remove);
        currentHovers.clear();
        currentSelections.values().forEach(BoardSelection::remove);
        currentSelections.clear();
        lastBlockHovered.clear();
    }
}
