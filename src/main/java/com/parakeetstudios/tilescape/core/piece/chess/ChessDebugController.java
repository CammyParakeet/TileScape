package com.parakeetstudios.tilescape.core.piece.chess;

import com.parakeetstudios.tilescape.core.piece.PieceController;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public enum ChessDebugController implements PieceController {

    KING('K') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.OAK_PLANKS : Material.DARK_OAK_PLANKS;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.5F, 0.75F, 0.5F);
        }
    },
    QUEEN('Q') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.STRIPPED_OAK_LOG : Material.STRIPPED_DARK_OAK_LOG;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.5F, 0.9F, 0.5F);
        }
    },
    ROOK('R') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.OAK_FENCE_GATE : Material.DARK_OAK_FENCE_GATE;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.5F, 0.65F, 0.5F);
        }
    },
    KNIGHT('N') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.OAK_STAIRS : Material.DARK_OAK_STAIRS;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.5F, 0.7F, 0.5F);
        }
    },
    BISHOP('B') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.OAK_PLANKS : Material.DARK_OAK_PLANKS;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.35F, 0.9F, 0.35F);
        }
    },
    PAWN('P') {
        @Override
        protected Material getMaterial(boolean isWhite) {
            return (isWhite) ? Material.OAK_SLAB: Material.DARK_OAK_SLAB;
        }

        @Override
        public Vector3f getScale() {
            return new Vector3f(0.4F, 0.75F, 0.4F);
        }
    };

    private final char symbol;

    ChessDebugController(char symbol) {
        this.symbol = symbol;
    }

    // Type related material for debug spawn
    protected abstract Material getMaterial(boolean isWhite);
    public abstract Vector3f getScale();

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public Entity spawnModel(Location location, PieceColor color) {
        Vector3f S = getScale();
        float w = getScale().x; // model width
        Vector3f T = new Vector3f(w/2F, 0.0F, w/2F);

        BlockDisplay model = location.getWorld().spawn(location, BlockDisplay.class);
        //TODO refactor this to use config colors? - not needed for debug
        BlockData data = getMaterial(color.isStarter()).createBlockData();
        model.setPersistent(false); // debug
        model.setBlock(data);
        model.setTransformation(new Transformation(T, new AxisAngle4f(), S, new AxisAngle4f()));
        model.addScoreboardTag(""+symbol);

        return model;
    }


}
