package org.achymake.chairs.data;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.handlers.WorldHandler;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class Userdata {
    private Chairs getInstance() {
        return Chairs.getInstance();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private PersistentDataContainer getData(Player player) {
        return player.getPersistentDataContainer();
    }
    private NamespacedKey getKey(String key) {
        return getInstance().getKey(key);
    }
    private Entity getEntity(String uuidString) {
        return getInstance().getServer().getEntity(UUID.fromString(uuidString));
    }
    public float getReverseDirection(Player player) {
        float yaw = player.getLocation().getYaw();
        if (yaw >= -45.0F && yaw <= 45.0F) {
            return 180.0F;
        } else if (yaw >= 45.0F && yaw <= 135.0F) {
            return -90.0F;
        } else if (yaw >= 135.0F && yaw <= 180.0F) {
            return 0.0F;
        } else if (yaw >= -180.F && yaw <= -135.0F) {
            return 0.0F;
        } else if (yaw >= -135.0F && yaw <= -45.0F) {
            return 90.0F;
        } else return 0.0F;
    }
    public void setLastLocation(Player player) {
        var x = player.getLocation().getX();
        var y = player.getLocation().getX();
        var z = player.getLocation().getX();
        getData(player).set(getKey("last-location.x"), PersistentDataType.DOUBLE, x);
        getData(player).set(getKey("last-location.y"), PersistentDataType.DOUBLE, y);
        getData(player).set(getKey("last-location.z"), PersistentDataType.DOUBLE, z);
    }
    public Location getLastLocation(Player player) {
        var world = player.getWorld();
        var x = getData(player).get(getKey("last-location.x"), PersistentDataType.DOUBLE);
        var y = getData(player).get(getKey("last-location.y"), PersistentDataType.DOUBLE);
        var z = getData(player).get(getKey("last-location.z"), PersistentDataType.DOUBLE);
        var yaw = player.getLocation().getYaw();
        var pitch = player.getLocation().getPitch();
        return new Location(world, x, y, z, yaw, pitch);
    }
    public boolean hasChair(Player player) {
        return getData(player).has(getKey("chair"), PersistentDataType.STRING);
    }
    public void setChair(Player player, ArmorStand armorStand) {
        getData(player).set(getKey("chair"), PersistentDataType.STRING, armorStand.getUniqueId().toString());
    }
    public ArmorStand getChair(Player player) {
        if (hasChair(player)) {
            return (ArmorStand) getEntity(getData(player).get(getKey("chair"), PersistentDataType.STRING));
        } else return null;
    }
    public void dismount(Player player) {
        if (hasChair(player)) {
            var armorStand = getChair(player);
            if (armorStand != null) {
                var above = armorStand.getLocation().add(0,1,0).getBlock();
                if (getWorldHandler().isOccupied(above)) {
                    getWorldHandler().removeOccupied(above);
                }
                armorStand.remove();
            }
            player.teleport(getLastLocation(player));
            getData(player).remove(getKey("chair"));
            getData(player).remove(getKey("last-location.x"));
            getData(player).remove(getKey("last-location.y"));
            getData(player).remove(getKey("last-location.z"));
        }
    }
}