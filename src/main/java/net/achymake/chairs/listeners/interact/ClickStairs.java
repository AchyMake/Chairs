package net.achymake.chairs.listeners.interact;

import net.achymake.chairs.Chairs;
import net.achymake.chairs.settings.ChairSettings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickStairs implements Listener {
    public ClickStairs(Chairs plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onClickEvent(PlayerInteractEvent event){
        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))return;
        if (!event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR))return;
        if (!event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.AIR))return;
        if (!event.getPlayer().hasPermission("chairs.sit"))return;
        if (!event.getPlayer().isOnGround())return;
        if (event.getPlayer().isSneaking())return;
        if (!Tag.STAIRS.isTagged(event.getClickedBlock().getType()))return;
        if (!event.getBlockFace().equals(BlockFace.UP))return;
        ChairSettings.sitOnStairs(event.getPlayer(), event.getClickedBlock());
    }
}
