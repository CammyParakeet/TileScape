package com.parakeetstudios.tilescape.core.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BlockUtils {

    public static Block getTargetedBlock(Player player, double maxDistance) {
        Location eye = player.getEyeLocation();
        Vector direction = eye.getDirection().normalize();
        Block target = null;

        for (double d = 0; d <= maxDistance; d += 0.25) {
            Location checking = eye.add(direction.clone().multiply(0.25));
            Block block = checking.getBlock();

            if (!block.getType().equals(Material.AIR) && !block.getType().equals(Material.BARRIER)) {
                target = block;
                break;  // hit something that's not air, barrier
            }
        }
        return target;
    }

}
