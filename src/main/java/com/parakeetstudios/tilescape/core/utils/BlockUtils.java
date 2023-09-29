package com.parakeetstudios.tilescape.core.utils;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class BlockUtils {

    public static Block getTargetedBlockNaive(Player player, double maxDistance) {
        Location eye = player.getEyeLocation();
        Vector direction = eye.getDirection().normalize();
        Block target = null;

        Vector increment = direction.multiply(0.25);
        for (double d = 0; d <= maxDistance; d += 0.25) {
            eye.add(increment);
            Block block = eye.getBlock();

            if (!block.getType().equals(Material.AIR) && !block.getType().equals(Material.BARRIER)) {
                target = block;
                break;  // hit something that's not air, barrier
            }
        }
        return target;
    }


    public static Block getTargetedBlock(World world, Player player, double maxDistance) {
        RayTraceResult result = world.rayTraceBlocks(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                maxDistance);

        if (result != null) {
            Block target = result.getHitBlock();
            if (target != null && target.getType() != Material.AIR && target.getType() != Material.BARRIER) {
                // if we are here, we are targeting a visible block
                return target;
            }
        }
        return null;
    }

}
