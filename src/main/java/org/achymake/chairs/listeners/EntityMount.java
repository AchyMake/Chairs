package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.files.Database;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityMountEvent;

public class EntityMount implements Listener {
    private Database getDatabase() {
        return Chairs.getDatabase();
    }
    public EntityMount(Chairs plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityMount(EntityMountEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        if (!event.getMount().getType().equals(EntityType.ARMOR_STAND))return;
        Player player = (Player) event.getEntity();
        ArmorStand armorStand = (ArmorStand) event.getMount();
        getDatabase().setChair(player, armorStand);
        if (event.isCancelled()) {
            getDatabase().removeOccupied(armorStand.getLocation().getBlock());
            getDatabase().dismount(player);
        }
    }
}
