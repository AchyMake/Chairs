package net.achymake.chairs.settings;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ChairSettings {
    public static PersistentDataContainer getData(Player player){
        return player.getPersistentDataContainer();
    }
    public static void sitOnCommand(Player player, Location location){
        location.add(0.5,-0.9,0.5);
        location.setYaw(player.getLocation().getYaw());
        location.setPitch(0);
        saveLocation(player);
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        addPassenger(armorStand,player);
    }
    public static void sitOnCarpets(Player player, Location location){
        saveLocation(player);
        location.add(0.5,0,0.5);
        location.setY(location.getY()-0.85);
        location.setYaw(player.getLocation().getYaw()+180);
        location.setPitch(0);
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        addPassenger(armorStand,player);
    }
    public static void sitOnSlabs(Player player, Location location){
        saveLocation(player);
        location.add(0.5,0,0.5);
        location.setY(location.getY()-0.4);
        location.setYaw(player.getLocation().getYaw()+180);
        location.setPitch(0);
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        addPassenger(armorStand,player);
    }

    public static void sitOnStairs(Player player, Block block){
        if (!((Stairs) block.getBlockData()).getHalf().equals(Bisected.Half.BOTTOM))return;
        if (((Stairs) block.getBlockData()).getFacing().equals(BlockFace.WEST)){
            if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.STRAIGHT)){
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-90);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_LEFT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-160);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_RIGHT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-20);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            }
        } else if (((Stairs) block.getBlockData()).getFacing().equals(BlockFace.SOUTH)) {
            if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.STRAIGHT)){
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-180);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_LEFT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(110);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_RIGHT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-110);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            }
        } else if (((Stairs) block.getBlockData()).getFacing().equals(BlockFace.NORTH)) {
            if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.STRAIGHT)){
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(0);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_LEFT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(-70);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_RIGHT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(70);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            }
        } else if (((Stairs) block.getBlockData()).getFacing().equals(BlockFace.EAST)) {
            if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.STRAIGHT)){
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(90);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_LEFT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY() - 0.4);
                location.setYaw(20);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            } else if (((Stairs) block.getBlockData()).getShape().equals(Stairs.Shape.INNER_RIGHT)) {
                saveLocation(player);
                Location location = block.getLocation().add(0.5,0,0.5);
                location.setY(block.getY()-0.4);
                location.setYaw(160);
                location.setPitch(0);
                ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                addPassenger(armorStand,player);
            }
        }
    }
    public static void saveLocation(Player player){
        getData(player).set(NamespacedKey.minecraft("chair-location.x"), PersistentDataType.DOUBLE,player.getLocation().getX());
        getData(player).set(NamespacedKey.minecraft("chair-location.y"), PersistentDataType.DOUBLE,player.getLocation().getY());
        getData(player).set(NamespacedKey.minecraft("chair-location.z"), PersistentDataType.DOUBLE,player.getLocation().getZ());
    }
    public static void addPassenger(Entity entity, Player player){
        ArmorStand armorStand = (ArmorStand) entity;
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.addPassenger(player);
    }
    public static boolean isSitting(Player player){
        return getData(player).has(NamespacedKey.minecraft("chair-location.x"),PersistentDataType.DOUBLE);
    }
    public static void stand(Player player, Entity entity){
        player.teleport(ChairSettings.getLocation(player));
        ChairSettings.removeLocation(player);
        entity.remove();
    }
    public static Location getLocation(Player player){
        Double x = getData(player).get(NamespacedKey.minecraft("chair-location.x"), PersistentDataType.DOUBLE);
        Double y = getData(player).get(NamespacedKey.minecraft("chair-location.y"), PersistentDataType.DOUBLE);
        Double z = getData(player).get(NamespacedKey.minecraft("chair-location.z"), PersistentDataType.DOUBLE);
        Location location = new Location(player.getWorld(),x,y,z,player.getLocation().getYaw(),player.getLocation().getPitch());
        return location;
    }
    public static void removeLocation(Player player){
        getData(player).remove(NamespacedKey.minecraft("chair-location.x"));
        getData(player).remove(NamespacedKey.minecraft("chair-location.y"));
        getData(player).remove(NamespacedKey.minecraft("chair-location.z"));
    }
}
