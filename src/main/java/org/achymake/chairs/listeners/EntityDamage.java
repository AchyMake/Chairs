package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.files.Database;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    private Database getDatabase() {
        return Chairs.getDatabase();
    }
    public EntityDamage(Chairs plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(EntityDamageEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        Player player = (Player) event.getEntity();
        if (!getDatabase().hasChair(player))return;
        getDatabase().removeOccupied(player.getLocation().getBlock());
        getDatabase().dismount(player);
    }
}
