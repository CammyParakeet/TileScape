package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;
import com.parakeetstudios.tilescape.game.BoardGame;

import java.util.UUID;

public interface TaskFactory {

    HoverDisplayTask createHoverTask(UUID playerID, BoardGame playersGame);

    //TODO other tasks?

}
