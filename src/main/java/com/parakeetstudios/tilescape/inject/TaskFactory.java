package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.core.tasks.HoverDisplayTask;

public interface TaskFactory {

    //TODO offload tasks

    HoverDisplayTask createHoverTask();

}
