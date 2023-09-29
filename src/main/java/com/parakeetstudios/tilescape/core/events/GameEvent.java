package com.parakeetstudios.tilescape.core.events;

import com.parakeetstudios.tilescape.game.BoardGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class GameEvent extends Event {

    private final UUID playerID;
    private final BoardGame game;
    private static final HandlerList handlers = new HandlerList();

    public GameEvent(UUID playerID, BoardGame game) {
        this.playerID = playerID;
        this.game = game;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerID);
    }

    public UUID getPlayerID() {
        return playerID;
    }

    public BoardGame getGame() {
        return game;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
