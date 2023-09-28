package com.parakeetstudios.tilescape.inject;

import com.parakeetstudios.tilescape.core.utils.PieceRenderer;
import org.jetbrains.annotations.NotNull;

public interface RendererFactory {
    PieceRenderer create(@NotNull String game);
}
