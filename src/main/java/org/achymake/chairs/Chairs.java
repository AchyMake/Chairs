package org.achymake.chairs;

import org.achymake.chairs.commands.ChairsCommand;
import org.achymake.chairs.data.Message;
import org.achymake.chairs.data.Userdata;
import org.achymake.chairs.handlers.*;
import org.achymake.chairs.listeners.*;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public final class Chairs extends JavaPlugin {
    private static Chairs instance;
    private Message message;
    private Userdata userdata;
    private EntityHandler entityHandler;
    private MaterialHandler materialHandler;
    private ScheduleHandler scheduleHandler;
    private WorldHandler worldHandler;
    private UpdateChecker updateChecker;
    private BukkitScheduler bukkitScheduler;
    private PluginManager pluginManager;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message();
        userdata = new Userdata();
        entityHandler = new EntityHandler();
        materialHandler = new MaterialHandler();
        scheduleHandler = new ScheduleHandler();
        worldHandler = new WorldHandler();
        updateChecker = new UpdateChecker();
        bukkitScheduler = getServer().getScheduler();
        pluginManager = getServer().getPluginManager();
        commands();
        events();
        reload();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getOnlinePlayers().forEach(this::dismount);
        getScheduleHandler().disable();
        sendInfo("Disabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    private void commands() {
        new ChairsCommand();
    }
    private void events() {
        new BlockBreak();
        new EntityDamage();
        new EntityDamageByEntity();
        new EntityDismount();
        new PlayerDeath();
        new PlayerInteract();
        new PlayerJoin();
        new PlayerQuit();
        new PlayerTeleport();
    }
    public void reload() {
        var file = new File(getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfig().load(file);
            } catch (IOException | InvalidConfigurationException e) {
                sendWarning(e.getMessage());
            }
        } else {
            getConfig().options().copyDefaults(true);
            try {
                getConfig().save(file);
            } catch (IOException e) {
                sendWarning(e.getMessage());
            }
        }
        getMessage().reload();
    }
    public Collection<? extends Player> getOnlinePlayers() {
        return getServer().getOnlinePlayers();
    }
    public PluginManager getPluginManager() {
        return pluginManager;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public MaterialHandler getMaterialHandler() {
        return materialHandler;
    }
    public EntityHandler getEntityHandler() {
        return entityHandler;
    }
    public Userdata getUserdata() {
        return userdata;
    }
    public Message getMessage() {
        return message;
    }
    public static Chairs getInstance() {
        return instance;
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
    public NamespacedKey getKey(String key) {
        return new NamespacedKey(this, key);
    }
    public void dismount(Player player) {
        getUserdata().dismount(player);
    }
    public static boolean hasChair(Player player) {
        return getInstance().getUserdata().hasChair(player);
    }
}