package com.maximde.speedrun.events;

import com.maximde.speedrun.Main;
import com.maximde.speedrun.commands.CommandTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent extends PlayerListener {
    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(Main.PREFIX+"§c"+player.getName()+" left the server");
        Bukkit.getScheduler().cancelTask(CommandTimer.timer);
        CommandTimer.hour = 0;
        CommandTimer.sec = 0;
        CommandTimer.min = 0;
        CommandTimer.setOut(CommandTimer.hour, CommandTimer.min, CommandTimer.sec);
    }
}
