package com.maximde.speedrun;

import com.maximde.speedrun.commands.CommandRandom;
import com.maximde.speedrun.commands.CommandReset;
import com.maximde.speedrun.commands.CommandTime;
import com.maximde.speedrun.commands.CommandTimer;
import com.maximde.speedrun.events.DeathEvent;
import com.maximde.speedrun.events.DragonDeathEvent;
import com.maximde.speedrun.events.JoinEvent;
import com.maximde.speedrun.events.LeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main INSTANCE;
    public static String PREFIX = "§6SpeedRuns §7>> §f";

    @Override
    public void onEnable() {
        INSTANCE = this;
        System.out.println("Enabled speedrun plugin by MaximDe");
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_JOIN, new JoinEvent(), Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, new LeaveEvent(), Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.ENTITY_DEATH, new DragonDeathEvent(), Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, new DeathEvent(), Event.Priority.Normal, this);
        getCommand("reset").setExecutor(new CommandReset());
        getCommand("timer").setExecutor(new CommandTimer());
        getCommand("time").setExecutor(new CommandTime());
        getCommand("random").setExecutor(new CommandRandom());
    }

    @Override
    public void onDisable() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("§6Speedruns >> " +
                    "§aRestarting server! rejoin in a few seconds"+
                    " §7Hosted by Maxim");
        }
    }
}
