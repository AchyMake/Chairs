package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.data.Database;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public record PlayerInteract(Chairs plugin) implements Listener {
    private Database getDatabase() {
        return plugin.getDatabase();
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
        if (event.getClickedBlock() == null)return;
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        if (!event.getBlockFace().equals(BlockFace.UP))return;
        if (!getDatabase().isAboveAir(block))return;
        if (!player.getInventory().getItemInMainHand().getType().isAir())return;
        if (!player.getInventory().getItemInOffHand().getType().isAir())return;
        if (!player.isOnGround())return;
        if (player.isSneaking())return;
        if (plugin.isSitting(player))return;
        if (getDatabase().isOccupied(block))return;
        if (Tag.CARPETS.isTagged(block.getType())) {
            if (!player.hasPermission("chairs.sit.carpets"))return;
            getDatabase().setOccupied(block);
            Location location = block.getLocation().add(0.5,-0.95,0.5);
            location.setYaw(player.getLocation().getYaw() + 180.0F);
            location.setPitch(0.0F);
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.addPassenger(player);
        } else if (block.getType().equals(Material.HAY_BLOCK)) {
            if (!player.hasPermission("chairs.sit.hay_block"))return;
            getDatabase().setOccupied(block);
            Location location = block.getLocation().add(0.5,-0.25,0.5);
            location.setYaw(player.getLocation().getYaw() + 180.0F);
            location.setPitch(0.0F);
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.addPassenger(player);
        } else if (block.getType().equals(Material.SCAFFOLDING)) {
            if (!player.hasPermission("chairs.sit.scaffolding"))return;
            getDatabase().setOccupied(block);
            Location location = block.getLocation().add(0.5,0.0,0.5);
            location.setYaw(player.getLocation().getYaw() + 180.0F);
            location.setPitch(0.0F);
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.addPassenger(player);
        } else if (Tag.SLABS.isTagged(block.getType())) {
            if (!getDatabase().isBottom(getDatabase().getSlab(block)))return;
            if (!player.hasPermission("chairs.sit.slabs"))return;
            getDatabase().setOccupied(block);
            Location location = block.getLocation().add(0.5,-0.5,0.5);
            location.setYaw(player.getLocation().getYaw() + 180.0F);
            location.setPitch(0.0F);
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.addPassenger(player);
        } else if (Tag.STAIRS.isTagged(block.getType())) {
            if (!getDatabase().isBottom(getDatabase().getStair(block)))return;
            if (!player.hasPermission("chairs.sit.stairs"))return;
            if (getDatabase().isEast(getDatabase().getStair(block))) {
                if (getDatabase().isStraight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(90.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerLeft(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(25.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerRight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(155.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                }
            } else if (getDatabase().isNorth(getDatabase().getStair(block))) {
                if (getDatabase().isStraight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(0.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerLeft(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(-65.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerRight(getDatabase().getStair(event.getClickedBlock()))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(65.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                }
            } else if (getDatabase().isSouth(getDatabase().getStair(block))) {
                if (getDatabase().isStraight(getDatabase().getStair(event.getClickedBlock()))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(180.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerLeft(getDatabase().getStair(event.getClickedBlock()))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(115.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerRight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(-115.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                }
            } else if (getDatabase().isWest(getDatabase().getStair(block))) {
                if (getDatabase().isStraight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(-90.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerLeft(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(-155.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                } else if (getDatabase().isInnerRight(getDatabase().getStair(block))) {
                    getDatabase().setOccupied(block);
                    Location location = block.getLocation().add(0.5,-0.4,0.5);
                    location.setYaw(-25.0F);
                    location.setPitch(0.0F);
                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                    armorStand.setVisible(false);
                    armorStand.setGravity(false);
                    armorStand.setSmall(true);
                    armorStand.addPassenger(player);
                }
            }
        }
    }
}