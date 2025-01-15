package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.data.Userdata;
import org.achymake.chairs.handlers.EntityHandler;
import org.achymake.chairs.handlers.MaterialHandler;
import org.achymake.chairs.handlers.WorldHandler;
import org.bukkit.Tag;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.PluginManager;

public class PlayerInteract implements Listener {
    private Chairs getInstance() {
        return Chairs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private EntityHandler getEntityHandler() {
        return getInstance().getEntityHandler();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private MaterialHandler getMaterials() {
        return getInstance().getMaterialHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerInteract() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
        if (event.getClickedBlock() == null)return;
        if (!event.getBlockFace().equals(BlockFace.UP))return;
        if (event.getHand() != EquipmentSlot.HAND)return;
        var block = event.getClickedBlock();
        var player = event.getPlayer();
        if (!player.isOnGround())return;
        if (!getMaterials().isAir(player.getInventory().getItemInMainHand()))return;
        if (!getMaterials().isAir(player.getInventory().getItemInOffHand()))return;
        if (!getMaterials().isAir(block.getLocation().add(0, 1, 0).getBlock()))return;
        if (player.isSneaking())return;
        if (getUserdata().hasChair(player))return;
        if (getWorldHandler().isOccupied(block))return;
        if (Tag.CARPETS.isTagged(block.getType())) {
            if (!player.hasPermission("chairs.event.sit.carpets"))return;
            getUserdata().setLastLocation(player);
            var location = block.getLocation().add(0.5, -0.90, 0.5);
            location.setYaw(getUserdata().getReverseDirection(player));
            location.setPitch(0.0F);
            var armorStand = getEntityHandler().spawnArmorStand(location);
            getWorldHandler().setOccupied(block, player);
            getUserdata().setChair(player, armorStand);
            armorStand.addPassenger(player);
        } else if (Tag.SLABS.isTagged(block.getType())) {
            if (getMaterials().isWaterLogged(block))return;
            if (!player.hasPermission("chairs.event.sit.slabs"))return;
            if (!getMaterials().isBottom(getMaterials().getSlab(block)))return;
            getUserdata().setLastLocation(player);
            var location = block.getLocation().add(0.5, -0.45, 0.5);
            location.setYaw(getUserdata().getReverseDirection(player));
            location.setPitch(0.0F);
            var armorStand = getEntityHandler().spawnArmorStand(location);
            getWorldHandler().setOccupied(block, player);
            getUserdata().setChair(player, armorStand);
            armorStand.addPassenger(player);
        } else if (Tag.STAIRS.isTagged(block.getType())) {
            if (getMaterials().isWaterLogged(block))return;
            if (!player.hasPermission("chairs.event.sit.stairs"))return;
            if (!getMaterials().isBottom(getMaterials().getStair(block)))return;
            if (getMaterials().isEast(getMaterials().getStair(block))) {
                if (!getMaterials().isStraight(getMaterials().getStair(block)))return;
                getUserdata().setLastLocation(player);
                var location = block.getLocation().add(0.3, -0.45, 0.5);
                location.setYaw(90.0F);
                location.setPitch(0.0F);
                var armorStand = getEntityHandler().spawnArmorStand(location);
                getWorldHandler().setOccupied(block, player);
                getUserdata().setChair(player, armorStand);
                armorStand.addPassenger(player);
            } else if (getMaterials().isNorth(getMaterials().getStair(block))) {
                if (!getMaterials().isStraight(getMaterials().getStair(block)))return;
                getUserdata().setLastLocation(player);
                var location = block.getLocation().add(0.5, -0.45, 0.7);
                location.setYaw(0.0F);
                location.setPitch(0.0F);
                var armorStand = getEntityHandler().spawnArmorStand(location);
                getWorldHandler().setOccupied(block, player);
                getUserdata().setChair(player, armorStand);
                armorStand.addPassenger(player);
            } else if (getMaterials().isSouth(getMaterials().getStair(block))) {
                if (!getMaterials().isStraight(getMaterials().getStair(block)))return;
                getUserdata().setLastLocation(player);
                var location = block.getLocation().add(0.5, -0.45, 0.3);
                location.setYaw(180.0F);
                location.setPitch(0.0F);
                var armorStand = getEntityHandler().spawnArmorStand(location);
                getWorldHandler().setOccupied(block, player);
                getUserdata().setChair(player, armorStand);
                armorStand.addPassenger(player);
            } else if (getMaterials().isWest(getMaterials().getStair(block))) {
                if (!getMaterials().isStraight(getMaterials().getStair(block)))return;
                getUserdata().setLastLocation(player);
                var location = block.getLocation().add(0.7, -0.45, 0.5);
                location.setYaw(-90.0F);
                location.setPitch(0.0F);
                var armorStand = getEntityHandler().spawnArmorStand(location);
                getWorldHandler().setOccupied(block, player);
                getUserdata().setChair(player, armorStand);
                armorStand.addPassenger(player);
            }
        }
    }
}