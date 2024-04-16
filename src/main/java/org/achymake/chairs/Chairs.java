package org.achymake.chairs;

import org.achymake.chairs.commands.*;
import org.achymake.chairs.data.Database;
import org.achymake.chairs.data.Message;
import org.achymake.chairs.listeners.*;
import org.achymake.chairs.net.UpdateChecker;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Chairs extends JavaPlugin {
    private static Chairs instance;
    private static Database database;
    private static Message message;
    private static UpdateChecker updateChecker;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message(this);
        database = new Database();
        updateChecker = new UpdateChecker(this);
        reload();
        commands();
        events();
        getMessage().sendLog(Level.INFO, "Enabled " + getName() + " " + getDescription().getVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        getMessage().sendLog(Level.INFO, "Disabled " + getName() + " " + getDescription().getVersion());
    }
    private void commands() {
        getCommand("chairs").setExecutor(new ChairsCommand());
        getCommand("sit").setExecutor(new SitCommand());
    }
    private void events() {
        getManager().registerEvents(new EntityDamage(this), this);
        getManager().registerEvents(new EntityDismount(this), this);
        getManager().registerEvents(new EntityMount(this), this);
        getManager().registerEvents(new NotifyUpdate(this), this);
        getManager().registerEvents(new PlayerInteract(this), this);
        getManager().registerEvents(new PlayerQuit(this), this);
        getManager().registerEvents(new PlayerTeleport(this), this);
    }
    public void reload() {
        File file = new File(getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfig().load(file);
            } catch (IOException | InvalidConfigurationException e) {
                getMessage().sendLog(Level.WARNING, e.getMessage());
            }
        } else {
            getConfig().options().copyDefaults(true);
            try {
                getConfig().save(file);
                getMessage().sendLog(Level.INFO, "created config.yml");
            } catch (IOException e) {
                getMessage().sendLog(Level.WARNING, e.getMessage());
            }
        }
    }
    private PluginManager getManager() {
        return getServer().getPluginManager();
    }
    public boolean isSitting(Player player) {
        return getDatabase().hasChair(player);
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public Message getMessage() {
        return message;
    }
    public Database getDatabase() {
        return database;
    }
    public static Chairs getInstance() {
        return instance;
    }
}
