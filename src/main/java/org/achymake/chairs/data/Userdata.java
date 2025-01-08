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
        var y = player.getLocation().getY();
        var z = player.getLocation().getZ();
        var data = getData(player);
        data.set(getKey("chairs-last-location-x"), PersistentDataType.DOUBLE, x);
        data.set(getKey("chairs-last-location-y"), PersistentDataType.DOUBLE, y);
        data.set(getKey("chairs-last-location-z"), PersistentDataType.DOUBLE, z);
    }
    public Location getLastLocation(Player player) {
        var world = player.getWorld();
        var data = getData(player);
        if (data.has(getKey("chairs-last-location-x")) &&
                data.has(getKey("chairs-last-location-y")) &&
                data.has(getKey("chairs-last-location-z"))) {
            var x = data.get(getKey("chairs-last-location-x"), PersistentDataType.DOUBLE);
            var y = data.get(getKey("chairs-last-location-y"), PersistentDataType.DOUBLE);
            var z = data.get(getKey("chairs-last-location-z"), PersistentDataType.DOUBLE);
            var yaw = player.getLocation().getYaw();
            var pitch = player.getLocation().getPitch();
            return new Location(world, x, y, z, yaw, pitch);
        } else {
            var x = player.getLocation().getX();
            var y = player.getLocation().getY() + 1;
            var z = player.getLocation().getZ();
            var yaw = player.getLocation().getYaw();
            var pitch = player.getLocation().getPitch();
            return new Location(world, x, y, z, yaw, pitch);
        }
    }
    public boolean hasChair(Player player) {
        return getData(player).has(getKey("chairs-chair"), PersistentDataType.STRING);
    }
    public void setChair(Player player, ArmorStand armorStand) {
        getData(player).set(getKey("chairs-chair"), PersistentDataType.STRING, armorStand.getUniqueId().toString());
    }
    public ArmorStand getChair(Player player) {
        if (hasChair(player)) {
            return (ArmorStand) getEntity(getData(player).get(getKey("chairs-chair"), PersistentDataType.STRING));
        } else return null;
    }
    public void dismount(Player player) {
        if (hasChair(player)) {
            var data = getData(player);
            var armorStand = getChair(player);
            if (armorStand != null) {
                var above = armorStand.getLocation().add(0,1,0).getBlock();
                if (getWorldHandler().isOccupied(above)) {
                    getWorldHandler().removeOccupied(above);
                }
                armorStand.remove();
                if (data.has(getKey("chairs-chair"))) {
                    data.remove(getKey("chairs-chair"));
                }
            }
            player.teleport(getLastLocation(player));
            if (data.has(getKey("chairs-last-location-x"))) {
                data.remove(getKey("chairs-last-location-x"));
            }
            if (data.has(getKey("chairs-last-location-y"))) {
                data.remove(getKey("chairs-last-location-y"));
            }
            if (data.has(getKey("chairs-last-location-z"))) {
                data.remove(getKey("chairs-last-location-z"));
            }
        }
    }
}