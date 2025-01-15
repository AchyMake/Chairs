package org.achymake.chairs.handlers;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class EntityHandler {
    public ArmorStand spawnArmorStand(Location location) {
        var armorStand = location.getWorld().createEntity(location, ArmorStand.class);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        location.getWorld().addEntity(armorStand);
        return armorStand;
    }
}