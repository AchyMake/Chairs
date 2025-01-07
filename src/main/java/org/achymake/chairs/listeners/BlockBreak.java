package org.achymake.chairs.listeners;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.data.Userdata;
import org.achymake.chairs.handlers.WorldHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.PluginManager;

public class BlockBreak implements Listener {
    private Chairs getInstance() {
        return Chairs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public BlockBreak() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        if (!getWorldHandler().isOccupied(event.getBlock()))return;
        getUserdata().dismount(getWorldHandler().getOccupied(event.getBlock()));
    }
}