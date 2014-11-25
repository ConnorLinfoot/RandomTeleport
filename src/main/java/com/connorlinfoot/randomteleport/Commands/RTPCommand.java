package com.connorlinfoot.randomteleport.Commands;

import com.connorlinfoot.randomteleport.RandomTeleport;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RTPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.AQUA + "\"" + RandomTeleport.getPlugin().getDescription().getName() + "\" - Version: " + RandomTeleport.getPlugin().getDescription().getVersion());
        return true;
    }

}
