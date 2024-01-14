package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NotifyUpdate implements Listener {
    private Chairs getPlugin() {
        return Chairs.getInstance();
    }
    public NotifyUpdate(Chairs plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("chairs.command.reload"))return;
        getPlugin().getUpdate(player);
    }
}
