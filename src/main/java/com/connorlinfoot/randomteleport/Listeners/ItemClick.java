package com.connorlinfoot.randomteleport.Listeners;

import com.connorlinfoot.randomteleport.RandomTeleport;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ItemClick implements Listener {

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if (item == null || item.getItemMeta().getDisplayName() == null) return;
        if (event.isCancelled()) return;
        if (item.getType() == Material.STICK && item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Random Teleport " + ChatColor.GRAY + "(Right Click)")) {
            /* Randomly Teleport the player */
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
            Integer randX = randInt(-RandomTeleport.XRadius, RandomTeleport.XRadius);
            Integer randZ = randInt(-RandomTeleport.ZRadius, RandomTeleport.ZRadius);
            Location location = player.getLocation();
            location.setX(randX);
            location.setZ(randZ);
            player.teleport(location);
            player.sendMessage(RandomTeleport.Prefix + "You have been randomly teleported");
        }
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
