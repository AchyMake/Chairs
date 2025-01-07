package org.achymake.chairs.handlers;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class EntityHandler {
    public ArmorStand spawnArmorStand(Location location) {
        var armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setSmall(true);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        return armorStand;
    }
}