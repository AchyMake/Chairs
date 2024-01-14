package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.files.Database;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class EntityDismount implements Listener {
    private Database getDatabase() {
        return Chairs.getDatabase();
    }
    public EntityDismount(Chairs plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDismount(EntityDismountEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        if (!event.getDismounted().getType().equals(EntityType.ARMOR_STAND))return;
        getDatabase().removeOccupied(event.getDismounted().getLocation().add(0,1,0).getBlock());
        getDatabase().dismount((Player) event.getEntity());
    }
}
