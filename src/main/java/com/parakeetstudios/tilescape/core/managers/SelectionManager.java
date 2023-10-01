package com.parakeetstudios.tilescape.core.managers;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.events.GameEvents;
import com.parakeetstudios.tilescape.core.selector.BoardSelection;
import com.parakeetstudios.tilescape.core.selector.Selection;
import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.inject.TaskFactory;
import com.parakeetstudios.tilescape.managers.CentralGameRegistry;
import com.parakeetstudios.tilescape.managers.UtilityManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
    private final CentralGameRegistry gameRegistry;
    private final TaskFactory taskFactory;

    private final Map<UUID, HoverDisplayTask> delegatedHoverTasks = new ConcurrentHashMap<>();
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
                            @NotNull CentralGameRegistry gameRegistry,
                            @NotNull TaskFactory taskFactory,
                            @NotNull TilescapeConfig cfg)
    {
        this.plugin = plugin;
        this.cfg = cfg;
        this.gameRegistry = gameRegistry;
        this.taskFactory = taskFactory;
    }


    // destroys the players hover-task
    public void clearHoverTask(UUID playerID) {
        delegatedHoverTasks.computeIfPresent(playerID, (key, hover) -> {
            hover.cancel();
            return null;
        });
    }

    // cancels the players hover-task
    public void cancelHoverTask(UUID playerID) {
        HoverDisplayTask hover = delegatedHoverTasks.get(playerID);
        if (hover != null) hover.cancel();
    }

    // starts the players hover-task
    public void startHoverTask(UUID playerID, BoardGame game) {
        HoverDisplayTask task = delegatedHoverTasks.get(playerID);

        // task must be recreated to schedule if cancelled
        if (task == null || task.isCancelled()) {
            task = taskFactory.createHoverTask(playerID, game);
            delegatedHoverTasks.put(playerID, task);
            task.runTaskTimer(plugin, 0, 2);
        }
    }


    // clears the players hover display
    public void clearHover(UUID playerID) {
        currentHovers.computeIfPresent(playerID, (key, hover) -> {
            hover.remove();
            lastBlockHovered.remove(playerID);
            return null;
        });
    }

    // update hover to a given block TODO change to interaction entity
    public void updateHover(UUID playerID, Block block) {
        // return if we haven't changed blocks
        if (block.equals(lastBlockHovered.get(playerID))) return;

        clearHover(playerID);

        //currentHovers.put(playerID, TODO some spawn method for selections)
        lastBlockHovered.put(playerID, block);
    }


    public void clearSelection(UUID playerID) {
        Selection selection = currentSelections.get(playerID);
        if (selection != null) selection.remove();
    }


    public void updateSelection(UUID playerID, Color color) {
        // check if it has a piece
        Block currentBlock = lastBlockHovered.get(playerID);
        Optional<Piece> hasPiece = getSelectedPiece(playerID, currentBlock);
        if (hasPiece.isEmpty()) {
            //TODO doesnt have piece - handle this
            return;
        }

        clearSelection(playerID);

        BoardSelection selection = new BoardSelection(currentBlock, color, hasPiece.get());
        currentSelections.put(playerID, selection);
    }

    private Optional<Piece> getSelectedPiece(UUID playerID, Block selected) {
        Board board = gameRegistry.getPlayersGame(playerID).orElseThrow().getBoard();
        //return board.getPieceAt(); TODO method to get boardselection of a block?
        return null;
    }


    /**
     *  Selection Listeners
     */

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerClick(PlayerInteractEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();

        // if they have no hovers - they aren't in game so return
        if (!currentHovers.containsKey(playerID)) return;

        // retrieve players game if exists - they should here though
        BoardGame game = gameRegistry.getPlayersGame(playerID).orElse(null);

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


    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerEnterGame(GameEvents.PlayerEnterGameEvent event) {
        UUID playerID = event.getPlayerID();
        startHoverTask(playerID, event.getGame());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLeaveGame(GameEvents.PlayerLeaveGameEvent event) {
        UUID playerID = event.getPlayerID();
        clearHoverTask(playerID);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLeave(PlayerQuitEvent event) {
        UUID playerID = event.getPlayer().getUniqueId();
        clearHoverTask(playerID);
        clearHover(playerID);
        clearSelection(playerID);
        //TODO other storage logic with player who left?
    }

    /**
     * Setup/Cleanup
     */

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {
        delegatedHoverTasks.values().forEach(HoverDisplayTask::cancel);
        delegatedHoverTasks.clear();

        currentHovers.values().forEach(Entity::remove);
        currentHovers.clear();

        currentSelections.values().forEach(BoardSelection::remove);
        currentSelections.clear();

        lastBlockHovered.clear();
    }
}
