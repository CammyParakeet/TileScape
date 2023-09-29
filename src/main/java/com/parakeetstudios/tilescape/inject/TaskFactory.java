package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;

import java.util.UUID;

public interface TaskFactory {

    HoverDisplayTask createHoverTask(UUID playerID);

    //TODO other tasks?

}
