package net.achymake.chairs.settings;

import net.achymake.chairs.Chairs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final Chairs plugin;
    private final int resourceId;
    public UpdateChecker(Chairs plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }
    public void getVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try {
                InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId)).openStream();
                Scanner scanner = new Scanner(inputStream);
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                    scanner.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                this.plugin.getLogger().info("Unable to check for updates: " + e.getMessage());
            }
        });
    }
    public static void getUpdate(Chairs plugin){
        if (plugin.getConfig().getBoolean("notify-update")) {
            (new UpdateChecker(plugin, 104881)).getVersion((latest) -> {
                if (plugin.getDescription().getVersion().equalsIgnoreCase(latest)) {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&e"+plugin.getName()+"&6&l]&r You are using the latest version"));
                } else {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&e"+plugin.getName()+"&6&l]&r &cNew update: &f" + latest));
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[&e"+plugin.getName()+"&6&l]&r &cCurrent version: &f" + plugin.getDescription().getVersion()));
                }
            });
        }
    }
}
