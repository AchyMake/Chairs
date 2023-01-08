package net.achymake.chairs.command.sub;

import net.achymake.chairs.command.ChairsSubCommand;
import net.achymake.chairs.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChairsReload extends ChairsSubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "reload config";
    }

    @Override
    public String getSyntax() {
        return "/chairs reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            Config.reload();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Chairs reloaded"));
        }
    }
}