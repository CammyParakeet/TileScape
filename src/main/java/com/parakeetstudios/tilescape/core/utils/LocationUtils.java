package com.parakeetstudios.tilescape.core.utils;

import com.parakeetstudios.tilescape.game.board.BoardPosition;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class LocationUtils {

    public static float FacingToYaw(@NotNull String facing) {
        return switch (facing.toLowerCase()) {
            case "north" -> 180.0F;
            case "east" -> -90.0F;
            case "west" -> 90.0F;
            default -> 0.0F;
        };
    }

    public static Location giveRandomGameLocation(@NotNull List<Location> gameLocations) {
        if (gameLocations.isEmpty()) return null;

        Random r = new Random();
        int index = r.nextInt(gameLocations.size());
        return gameLocations.get(index);
    }

    public static Location getLocFromBoardPos(BoardPosition pos, Location origin) {
        double blockX = origin.getBlockX() + pos.file() + 0.5;
        double blockZ = origin.getBlockZ() + pos.rank() + 0.5;
        return new Location(origin.getWorld(), blockX, origin.getBlockY(), blockZ);
    }

}
