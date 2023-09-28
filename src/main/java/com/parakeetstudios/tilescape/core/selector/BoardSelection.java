package com.parakeetstudios.tilescape.core.selector;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.Piece;
import org.bukkit.Color;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.jetbrains.annotations.NotNull;

public class BoardSelection implements Selection {

    private Block currentBlock;
    private BlockDisplay selectionDisplay;
    private Color color;
    private Piece currentPiece;

    public BoardSelection(
            Block currentBlock,
            Color color,
            Piece piece)
    {
        this.currentBlock = currentBlock;
        this.color = color;
        this.currentPiece = piece;
        //this.selectionDisplay = some spawn util method
    }


    @Override
    public Block getCurrentBlock() {
        return currentBlock;
    }

    @Override
    public BlockDisplay getDisplay() {
        return selectionDisplay;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Piece getSelectedPiece() {
        return currentPiece;
    }

    @Override
    public void remove() {
        this.currentPiece = null;
        this.selectionDisplay.remove();
        this.currentBlock = null;
    }
}
