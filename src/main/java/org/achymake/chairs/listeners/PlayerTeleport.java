package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.files.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport implements Listener {
    private Database getDatabase() {
        return Chairs.getDatabase();
    }
    public PlayerTeleport(Chairs plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (!getDatabase().hasChair(player))return;
        event.setCancelled(true);
        Chairs.send(player, "&cYou can't teleport while using a chair");
    }
}
