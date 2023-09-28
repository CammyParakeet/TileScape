package com.parakeetstudios.tilescape.core.utils;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.parakeetstudios.tilescape.data.ModelData;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.piece.Piece;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.jetbrains.annotations.NotNull;

public class PieceRenderer {

    private final TilescapeConfig cfg;
    private final String game;

    @Inject
    public PieceRenderer(@Assisted String game, @NotNull TilescapeConfig cfg) {
        this.game = game;
        this.cfg = cfg;
    }

    public Entity renderPiece(Piece piece, Location location) {
        ItemDisplay model = location.getWorld().spawn(location, ItemDisplay.class);
        model.setPersistent(false); // debug
        model.getChunk().setForceLoaded(true);

        ModelData data = cfg.getPieceModelData(game, piece);

        model.setItemStack(data.getModel());
        //model.setRotation(data.getRotation());
        //model.setTransformation(data.getTransformation());
        //model.setItemDisplayTransform(data.getDisplayMode());

        return model;
    }

    // does this need to be here
    public void updatePiece(Entity entity, Piece piece) {
        //TODO
    }

    // do we need this
    public void removePiece(Entity entity) {
        entity.remove();
    }

}
