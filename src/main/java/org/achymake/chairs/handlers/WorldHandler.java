package org.achymake.chairs.handlers;

import org.achymake.chairs.Chairs;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class WorldHandler {
    private Chairs getInstance() {
        return Chairs.getInstance();
    }
    private PersistentDataContainer getData(World world) {
        return world.getPersistentDataContainer();
    }
    private NamespacedKey getKey(String key) {
        return getInstance().getKey(key);
    }
    private Entity getEntity(String uuidString) {
        return getInstance().getServer().getEntity(UUID.fromString(uuidString));
    }
    public boolean isOccupied(Block block) {
        var world = block.getWorld();
        var x = block.getLocation().getBlockX();
        var y = block.getLocation().getBlockY();
        var z = block.getLocation().getBlockZ();
        var result = x+"-"+y+"-"+z;
        return getData(world).has(getKey(result), PersistentDataType.STRING);
    }
    public void setOccupied(Block block, Player player) {
        var world = block.getWorld();
        var x = block.getLocation().getBlockX();
        var y = block.getLocation().getBlockY();
        var z = block.getLocation().getBlockZ();
        var uuidString = player.getUniqueId().toString();
        var result = x+"-"+y+"-"+z;
        getData(world).set(getKey(result), PersistentDataType.STRING, uuidString);
    }
    public Player getOccupied(Block block) {
        var world = block.getWorld();
        var x = block.getLocation().getBlockX();
        var y = block.getLocation().getBlockY();
        var z = block.getLocation().getBlockZ();
        var result = x+"-"+y+"-"+z;
        return (Player) getEntity(getData(world).get(getKey(result), PersistentDataType.STRING));
    }
    public void removeOccupied(Block block) {
        var world = block.getWorld();
        var x = block.getLocation().getBlockX();
        var y = block.getLocation().getBlockY();
        var z = block.getLocation().getBlockZ();
        var result = x+"-"+y+"-"+z;
        if (getData(world).has(getKey(result))) {
            getData(world).remove(getKey(result));
        }
    }
}
