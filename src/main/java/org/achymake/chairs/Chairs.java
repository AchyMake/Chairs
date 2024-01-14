package org.achymake.chairs;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.achymake.chairs.commands.*;
import org.achymake.chairs.files.Database;
import org.achymake.chairs.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Chairs extends JavaPlugin {
    private static Chairs instance;
    public static Chairs getInstance() {
        return instance;
    }
    private static File folder;
    public static File getFolder() {
        return folder;
    }
    private static FileConfiguration configuration;
    public static FileConfiguration getConfiguration() {
        return configuration;
    }
    private static Logger logger;
    public static void sendLog(Level level, String message) {
        logger.log(level, message);
    }
    private static Database database;
    public static Database getDatabase() {
        return database;
    }
    @Override
    public void onEnable() {
        instance = this;
        folder = getDataFolder();
        configuration = getConfig();
        logger = getLogger();
        database = new Database();
        reload();
        commands();
        events();
        sendLog(Level.INFO, "Enabled " + getName() + " " + getDescription().getVersion());
        getUpdate();
    }
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        sendLog(Level.INFO, "Disabled " + getName() + " " + getDescription().getVersion());
    }
    private void commands() {
        getCommand("chairs").setExecutor(new ChairsCommand());
        getCommand("sit").setExecutor(new SitCommand());
    }
    private void events() {
        new EntityDamage(this);
        new EntityDismount(this);
        new EntityMount(this);
        new NotifyUpdate(this);
        new PlayerInteractCarpets(this);
        new PlayerInteractHayBlock(this);
        new PlayerInteractScaffolding(this);
        new PlayerInteractSlabs(this);
        new PlayerInteractStairsEast(this);
        new PlayerInteractStairsEastInnerLeft(this);
        new PlayerInteractStairsEastInnerRight(this);
        new PlayerInteractStairsNorth(this);
        new PlayerInteractStairsNorthInnerLeft(this);
        new PlayerInteractStairsNorthInnerRight(this);
        new PlayerInteractStairsSouth(this);
        new PlayerInteractStairsSouthInnerLeft(this);
        new PlayerInteractStairsSouthInnerRight(this);
        new PlayerInteractStairsWest(this);
        new PlayerInteractStairsWestInnerLeft(this);
        new PlayerInteractStairsWestInnerRight(this);
        new PlayerQuit(this);
        new PlayerTeleport(this);
    }
    public static void reload() {
        File file = new File(getFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfiguration().load(file);
            } catch (IOException | InvalidConfigurationException e) {
                sendLog(Level.WARNING, e.getMessage());
            }
        } else {
            getConfiguration().options().copyDefaults(true);
            try {
                getConfiguration().save(file);
                sendLog(Level.INFO, "created config.yml");
            } catch (IOException e) {
                sendLog(Level.WARNING, e.getMessage());
            }
        }
    }
    public static boolean isSitting(Player player) {
        return getDatabase().hasChair(player);
    }
    public static void getUpdate(Player player) {
        if (notifyUpdate()) {
            getLatest((latest) -> {
                if (!getInstance().getDescription().getVersion().equals(latest)) {
                    send(player,"&6" + getInstance().getName() + " Update:&f " + latest);
                    send(player,"&6Current Version: &f" + getInstance().getDescription().getVersion());
                }
            });
        }
    }
    public void getUpdate() {
        if (notifyUpdate()) {
            getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
                @Override
                public void run() {
                    getLatest((latest) -> {
                        sendLog(Level.INFO, "Checking latest release");
                        if (getDescription().getVersion().equals(latest)) {
                            sendLog(Level.INFO, "You are using the latest version");
                        } else {
                            sendLog(Level.INFO, "New Update: " + latest);
                            sendLog(Level.INFO, "Current Version: " + getDescription().getVersion());
                        }
                    });
                }
            });
        }
    }
    public static void getLatest(Consumer<String> consumer) {
        try {
            InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + 104881)).openStream();
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
                scanner.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            sendLog(Level.WARNING, e.getMessage());
        }
    }
    private static boolean notifyUpdate() {
        return getConfiguration().getBoolean("notify-update");
    }
    public static void send(ConsoleCommandSender sender, String message) {
        sender.sendMessage(message);
    }
    public static void send(Player player, String message) {
        player.sendMessage(addColor(message));
    }
    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(addColor(message)));
    }
    public static String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
