package com.parakeetstudios.tilescape.inject;

import com.google.inject.name.Named;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface BoardFactory {
    Board createBoard(@NotNull @Named("type") String type, @NotNull Location location);
}
