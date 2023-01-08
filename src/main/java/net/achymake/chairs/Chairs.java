package net.achymake.chairs;

import net.achymake.chairs.command.ChairsCommand;
import net.achymake.chairs.command.SitCommand;
import net.achymake.chairs.listeners.Events;
import net.achymake.chairs.settings.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chairs extends JavaPlugin {
    public static Chairs instance;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        instance = this;
        Events.start(this);
        getCommand("chairs").setExecutor(new ChairsCommand());
        getCommand("sit").setExecutor(new SitCommand());
        UpdateChecker.getUpdate(this);
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&eChairs&6&l]&r &aEnabled &fChairs " +this.getDescription().getVersion()));
    }
    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&eChairs&6&l]&r &cDisabled &fChairs " +this.getDescription().getVersion()));
    }
}
