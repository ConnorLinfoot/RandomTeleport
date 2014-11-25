package com.connorlinfoot.randomteleport.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
        }
    }

}
