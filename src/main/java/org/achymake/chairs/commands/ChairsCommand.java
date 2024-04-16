package org.achymake.chairs.commands;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.data.Message;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChairsCommand implements CommandExecutor, TabCompleter {
    private Chairs getPlugin() {
        return Chairs.getInstance();
    }
    private Message getMessage() {
        return getPlugin().getMessage();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                getMessage().send(player, "&6" + getPlugin().getName() + "&f " + getPlugin().getDescription().getVersion());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    getPlugin().reload();
                    getMessage().send(player, "&6Chairs:&f reloaded");
                    return true;
                }
            }
        }
        if (sender instanceof ConsoleCommandSender consoleCommandSender) {
            if (args.length == 0) {
                getMessage().send(consoleCommandSender, getPlugin().getName() + " " + getPlugin().getDescription().getVersion());
                return true;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    getPlugin().reload();
                    getMessage().send(consoleCommandSender, "Chairs: reloaded");
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
