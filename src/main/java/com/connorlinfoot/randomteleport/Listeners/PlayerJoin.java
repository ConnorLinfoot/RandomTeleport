package com.connorlinfoot.randomteleport.Listeners;

import com.connorlinfoot.randomteleport.RandomTeleport;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (RandomTeleport.getPlugin().getConfig().isSet("Joined Players")) {
            List<String> players = (ArrayList<String>) RandomTeleport.getPlugin().getConfig().getList("Joined Players");
            if (!players.contains(player.getUniqueId().toString())) {
                giveItem(player);
                players.add(player.getUniqueId().toString());
                RandomTeleport.getPlugin().getConfig().set("Joined Players", players);
            }
        } else {
            giveItem(player);
            List<String> players = new ArrayList<String>();
            players.add(player.getUniqueId().toString());
            RandomTeleport.getPlugin().getConfig().set("Joined Players", players);
        }
        RandomTeleport.getPlugin().saveConfig();
    }

    private static void giveItem(Player player) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.AQUA + "Random Teleport " + ChatColor.GRAY + "(Right Click)");
        itemStack.setItemMeta(itemMeta);
        player.getInventory().addItem(itemStack);
    }

}
