package org.achymake.chairs.commands;

import org.achymake.chairs.Chairs;
import org.achymake.chairs.files.Database;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SitCommand implements CommandExecutor, TabCompleter {
    private Database getDatabase() {
        return Chairs.getDatabase();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                if (player.isOnGround()) {
                    if (!player.getLocation().add(0,-1,0).getBlock().isEmpty()) {
                        if (!Chairs.isSitting(player)) {
                            if (!getDatabase().isOccupied(player.getLocation().getBlock())) {
                                Location location = player.getLocation().getBlock().getLocation().add(0.5, -0.9, 0.5);
                                location.setYaw(player.getLocation().getYaw());
                                location.setPitch(0.0F);
                                ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                                getDatabase().setChair(player, armorStand);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.addPassenger(player);
                                return true;
                            }
                        }
                    } else {
                        Chairs.sendActionBar(player,"&cYou have to stand on ground");
                        return true;
                    }
                } else {
                    Chairs.sendActionBar(player,"&cYou have to stand on ground");
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public List onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.EMPTY_LIST;
    }
}
