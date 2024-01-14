package org.achymake.chairs.commands;

import org.achymake.chairs.Chairs;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChairsCommand implements CommandExecutor, TabCompleter {
    private Chairs getPlugin() {
        return Chairs.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                Chairs.send(player, "&6" + getPlugin().getName() + "&f " + getPlugin().getDescription().getVersion());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Chairs.reload();
                    Chairs.send(player, "&6Chairs:&f reloaded");
                    return true;
                }
            }
        }
        if (sender instanceof ConsoleCommandSender consoleCommandSender) {
            if (args.length == 0) {
                Chairs.send(consoleCommandSender, getPlugin().getName() + " " + getPlugin().getDescription().getVersion());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Chairs.reload();
                    Chairs.send(consoleCommandSender, "Chairs: reloaded");
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (player.hasPermission("chairs.command.reload")) {
                    commands.add("reload");
                }
            }
        }
        return commands;
    }
}
