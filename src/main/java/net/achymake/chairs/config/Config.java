package net.achymake.chairs.config;

import net.achymake.chairs.Chairs;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    public static File configFile = new File(Chairs.instance.getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}