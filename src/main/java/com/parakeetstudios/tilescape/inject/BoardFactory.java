package com.parakeetstudios.tilescape.inject;

import com.google.inject.name.Named;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import org.bukkit.Location;

public interface BoardFactory {
    Board createBoard(@Named("type") String type, Location location, TilescapeConfig cfg);
}
