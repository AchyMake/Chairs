package net.achymake.chairs.command;

import net.achymake.chairs.command.sub.ChairsReload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChairsCommand implements CommandExecutor, TabCompleter {
    public final ArrayList<ChairsSubCommand> chairsSubCommands = new ArrayList<>();
    public ChairsCommand(){
        chairsSubCommands.add(new ChairsReload());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            Player player = (Player) sender;
            for (ChairsSubCommand commands : chairsSubCommands){
                if (args[0].equals(commands.getName())){
                    commands.perform(player,args);
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            for (ChairsSubCommand names : chairsSubCommands){
                commands.add(names.getName());
            }
            return commands;
        }
        return commands;
    }
}