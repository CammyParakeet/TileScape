package com.parakeetstudios.tilescape.core.selector;

import com.parakeetstudios.tilescape.game.piece.Piece;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;

public interface Selection {

    //TODO rework to use interaction?
    Block getCurrentBlock();

    BlockDisplay getDisplay();

    Color getColor();
    void setColor(Color color);

    Piece getSelectedPiece();

    void remove();
}
