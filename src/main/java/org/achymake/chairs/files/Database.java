package org.achymake.chairs.files;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Database {
    public PersistentDataContainer data(Player player) {
        return player.getPersistentDataContainer();
    }
    public PersistentDataContainer data(Block block) {
        return block.getChunk().getPersistentDataContainer();
    }
    public boolean hasChair(Player player) {
        return data(player).has(NamespacedKey.minecraft("chairs.chair"), PersistentDataType.STRING);
    }
    public void setChair(Player player, ArmorStand armorStand) {
        setLastLocation(player);
        data(player).set(NamespacedKey.minecraft("chairs.chair"), PersistentDataType.STRING, armorStand.getUniqueId().toString());
    }
    public ArmorStand getChair(Player player) {
        if (hasChair(player)) {
            return (ArmorStand) player.getServer().getEntity(UUID.fromString(data(player).get(NamespacedKey.minecraft("chairs.chair"), PersistentDataType.STRING)));
        } else {
            return null;
        }
    }
    public boolean isOccupied(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        return data(block).has(NamespacedKey.minecraft(x+"."+y+"."+z), PersistentDataType.STRING);
    }
    public void setOccupied(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        data(block).set(NamespacedKey.minecraft(x+"."+y+"."+z), PersistentDataType.STRING, "true");
    }
    public void removeOccupied(Block block) {
        int x = block.getX();
        int y = block.getY();
        int z = block.getZ();
        data(block).remove(NamespacedKey.minecraft(x+"."+y+"."+z));
    }
    public void setLastLocation(Player player) {
        data(player).set(NamespacedKey.minecraft("chairs.x"), PersistentDataType.DOUBLE, player.getLocation().getX());
        data(player).set(NamespacedKey.minecraft("chairs.y"), PersistentDataType.DOUBLE, player.getLocation().getY());
        data(player).set(NamespacedKey.minecraft("chairs.z"), PersistentDataType.DOUBLE, player.getLocation().getZ());
    }
    public Location getLastLocation(Player player) {
        return new Location(player.getWorld(), data(player).get(NamespacedKey.minecraft("chairs.x"), PersistentDataType.DOUBLE), data(player).get(NamespacedKey.minecraft("chairs.y"), PersistentDataType.DOUBLE), data(player).get(NamespacedKey.minecraft("chairs.z"), PersistentDataType.DOUBLE), player.getLocation().getYaw(), player.getLocation().getPitch());
    }
    public void dismount(Player player) {
        removeChair(player);
        teleportBack(player);
    }
    public void teleportBack(Player player) {
        player.teleport(getLastLocation(player));
    }
    public void removeChair(Player player) {
        if (getChair(player) != null) {
            getChair(player).remove();
            data(player).remove(NamespacedKey.minecraft("chairs.chair"));
        }
    }
    public Stairs getStair(Block block) {
        return (Stairs) block.getBlockData();
    }
    public Slab getSlab(Block block) {
        return (Slab) block.getBlockData();
    }
    public boolean isAboveAir(Block block) {
        return block.getLocation().add(0,1,0).getBlock().getType().isAir();
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
    public boolean isInnerLeft(Stairs stairs) {
        return stairs.getShape().equals(Stairs.Shape.INNER_LEFT);
    }
    public boolean isInnerRight(Stairs stairs) {
        return stairs.getShape().equals(Stairs.Shape.INNER_RIGHT);
    }
}
