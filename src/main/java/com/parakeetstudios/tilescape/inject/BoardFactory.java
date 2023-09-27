package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.game.board.Board;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface BoardFactory<B extends Board> {
    B createBoard(@NotNull Location location);
}
