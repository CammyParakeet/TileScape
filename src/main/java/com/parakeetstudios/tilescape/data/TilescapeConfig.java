package com.parakeetstudios.tilescape.data;

import com.parakeetstudios.tilescape.core.utils.LocationUtils;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.PieceColor;
import com.parakeetstudios.tilescape.utils.Matrix;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TilescapeConfig {

    private final FileConfiguration cfg;

    public TilescapeConfig(FileConfiguration cfg) {
        this.cfg = cfg;
    }

    public double getMaxRaytraceDistance() { return cfg.getDouble("RAY_TRACE_DISTANCE"); }

    public String getDefaultChessStateNotation() {
        return cfg.getString("DEFAULT_CHESS_STATE_NOTATION");
    }

    public String getDefaultChessSetup() {
        return cfg.getString("DEFAULT_CHESS_SETUP");
    }

    public String getDefaultChessMoveNotation() {
        return cfg.getString("DEFAULT_CHESS_MOVE_NOTATION");
    }

    /**
     * Collect pre-configured chess game locations
     * @return {@link List<>} of {@link Location}
     */
    public List<Location> getChessLocations() {
        final List<?> locationSections = cfg.getList("CHESS_LOCATIONS");
        if (locationSections == null) return null;

        List<ConfigurationSection> chessLocSections = locationSections.stream()
                .filter(ConfigurationSection.class::isInstance)
                .map(ConfigurationSection.class::cast).toList();

        List<Location> chessLocations = new ArrayList<>();

        for (ConfigurationSection section : chessLocSections) {
            double x = section.getDouble("x");
            double y = section.getDouble("y");
            double z = section.getDouble("z");
            float yaw = LocationUtils.FacingToYaw(Objects.requireNonNull(section.getString("facing")));

            Location loc = new Location(Bukkit.getWorld("world"), x, y, z, yaw, 0.0F);
            chessLocations.add(loc);
        }

        return chessLocations;
    }

    public String getRenderMode() { return cfg.getString("RENDER_MODE"); }

    /**
     * Generate a {@link ModelData} object from config
     * @param game classifier to extract game specific pieces
     * @param piece the piece to find data for
     * @return {@link ModelData} for the piece
     */
    public ModelData getPieceModelData(String game, Piece piece) {
        final ConfigurationSection gameSection = cfg.getConfigurationSection("MODELS." + getRenderMode() + game.toUpperCase());
        if (gameSection == null) {
            throw new IllegalArgumentException("No model data found for: " + game.toUpperCase());
        }

        final ConfigurationSection modelSection = gameSection.getConfigurationSection(String.valueOf(piece.getSymbol()));
        if (modelSection == null) {
            throw new IllegalArgumentException("No model data found for: " + piece.getSymbol());
        }

        ModelData data = new ModelData();
        PieceColor color = piece.getColor();

        // get model based on it's color classifier
        String modelName = modelSection.getString("MODEL_" + color.toString());
        Material modelMaterial = Material.getMaterial(modelName);
        if (modelMaterial == null) {
            throw new IllegalArgumentException("Invalid material: " + modelName);
        }

        data.setModel(new ItemStack(modelMaterial));

        // create a new transformation - will need better logic in future
        Matrix M = new Matrix();
        M.setScale(Objects.requireNonNull(modelSection.getVector("SCALE")));
        data.setTransformation(M.toTransformation());

        //TODO way to set rotation from facing - use enum

        return data;
    }


}
