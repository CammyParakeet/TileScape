package com.parakeetstudios.tilescape.core.commands.debug;

import com.google.inject.Inject;
import com.parakeetstudios.tilescape.managers.CentralGameRegistry;
import com.parakeetstudios.tilescape.managers.CentralQueueRegistry;
import com.parakeetstudios.tilescape.managers.QueueManager;
import com.parakeetstudios.tilescape.managers.games.ChessQueueManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;


public class JoinGameCmd implements CommandExecutor, TabCompleter {

    private final CentralQueueRegistry registry;

    public JoinGameCmd(CentralQueueRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length != 1) return false;

            UUID playerID = player.getUniqueId();

            if (registry.isPlayerInQueue(playerID)) {
                player.sendMessage("You are already in queue");
                return true;
            }

            QueueManager queue = registry.getQueue(args[0]);
            if (queue == null) {
                player.sendMessage(args[0] + " isn't available at this time");
                return true;
            }

            queue.addPlayerToQueue(playerID);
            player.sendMessage("You have been added to the Chess queue!");

        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return registry.getActiveQueueNames();
        }
        return null;
    }
}
