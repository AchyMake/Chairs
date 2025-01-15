package org.achymake.chairs.handlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.inventory.ItemStack;

public class MaterialHandler {
    public Material get(String materialName) {
        return Material.valueOf(materialName.toUpperCase());
    }
    public Stairs getStair(Block block) {
        return (Stairs) block.getBlockData();
    }
    public boolean isWaterLogged(Block block) {
        if (block.getBlockData() instanceof Stairs stairs) {
            return stairs.isWaterlogged();
        } else if (block.getBlockData() instanceof Slab slab) {
            return slab.isWaterlogged();
        } else return false;
    }
    public Slab getSlab(Block block) {
        return (Slab) block.getBlockData();
    }
    public boolean isBottom(Slab slab) {
        return slab.getType().equals(Slab.Type.BOTTOM);
    }
    public boolean isBottom(Stairs stairs) {
        return stairs.getHalf().equals(Bisected.Half.BOTTOM);
    }
    public boolean isEast(Stairs stairs) {
        return stairs.getFacing().equals(BlockFace.EAST);
    }
    public boolean isNorth(Stairs stairs) {
        return stairs.getFacing().equals(BlockFace.NORTH);
    }
    public boolean isSouth(Stairs stairs) {
        return stairs.getFacing().equals(BlockFace.SOUTH);
    }
    public boolean isWest(Stairs stairs) {
        return stairs.getFacing().equals(BlockFace.WEST);
    }
    public boolean isStraight(Stairs stairs) {
        return stairs.getShape().equals(Stairs.Shape.STRAIGHT);
    }
    public boolean isAir(ItemStack itemStack) {
        return itemStack == null || itemStack.getType().equals(get("air"));
    }
    public boolean isAir(Block block) {
        return block.getType().equals(get("air"));
    }
}