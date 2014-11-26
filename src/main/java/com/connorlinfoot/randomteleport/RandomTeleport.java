package com.connorlinfoot.randomteleport;

import com.connorlinfoot.randomteleport.Commands.RTPCommand;
import com.connorlinfoot.randomteleport.Listeners.ItemClick;
import com.connorlinfoot.randomteleport.Listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class RandomTeleport extends JavaPlugin {
    private static Plugin plugin;
    public static boolean SNAPSHOT = false;
    public static String Prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "RandomTeleport" + ChatColor.GRAY + "] " + ChatColor.RESET;
    public static int XRadius = 0;
    public static int ZRadius = 0;
    public static Material itemType = Material.STICK;

    @Override
    public void onEnable() {
        plugin = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        Material temp = Material.valueOf(getConfig().getString("Item Material"));
        if (temp != null) {
            itemType = temp;
        }
        XRadius = getConfig().getInt("X Radius");
        ZRadius = getConfig().getInt("Z Radius");

        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");
        console.sendMessage(ChatColor.AQUA + getDescription().getName());
        console.sendMessage(ChatColor.AQUA + "Version " + getDescription().getVersion());
        if (getDescription().getVersion().contains("SNAPSHOT")) {
            SNAPSHOT = true;
            console.sendMessage(ChatColor.RED + "You are running a snapshot build of " + getDescription().getName() + " please report bugs!");
            console.sendMessage(ChatColor.RED + "NO support will be given if running old snapshot build!");
        }
        console.sendMessage("");
        console.sendMessage(ChatColor.BLUE + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        console.sendMessage("");

        registerCommands(console);
        registerEvents(console);
    }

    @Override
    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private void registerEvents(ConsoleCommandSender console) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoin(), this);
        pluginManager.registerEvents(new ItemClick(), this);
        console.sendMessage(Prefix + "Events have been registered");
    }

    private void registerCommands(ConsoleCommandSender console) {
        getCommand("rtp").setExecutor(new RTPCommand());
        console.sendMessage(Prefix + "Commands have been registered");
    }
}
