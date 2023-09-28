package com.parakeetstudios.tilescape.core.selector;

import org.bukkit.Color;
import org.bukkit.entity.BlockDisplay;

public interface Selection {

    BlockDisplay getDisplay();

    Color getColor();
    void setColor(Color color);

    

}
