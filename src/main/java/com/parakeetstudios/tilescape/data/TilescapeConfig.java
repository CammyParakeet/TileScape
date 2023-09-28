package com.parakeetstudios.tilescape.data;

import com.parakeetstudios.tilescape.core.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TilescapeConfig {

    private final FileConfiguration cfg;

    public TilescapeConfig(FileConfiguration cfg) {
        this.cfg = cfg;
    }

    public String getDefaultChessStateNotation() {
        return cfg.getString("DEFAULT_CHESS_STATE_NOTATION");
    }

    public String getDefaultChessSetup() {
        return cfg.getString("DEFAULT_CHESS_SETUP");
    }

    public String getDefaultChessMoveNotation() {
        return cfg.getString("DEFAULT_CHESS_MOVE_NOTATION");
    }

    public String getChessModelType() { return cfg.getString("CHESS_MODEL_TYPE"); }

    public String getShogiModelType() { return cfg.getString("SHOGI_MODEL_TYPE"); }

    public List<Location> getChessLocations() {
        List<?> locationSections = cfg.getList("CHESS_LOCATIONS");
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


}
