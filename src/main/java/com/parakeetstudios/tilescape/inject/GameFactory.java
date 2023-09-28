package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.game.BoardGame;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public interface GameFactory<G extends BoardGame> {
    G create(List<UUID> playerIDs, Location location);
}
