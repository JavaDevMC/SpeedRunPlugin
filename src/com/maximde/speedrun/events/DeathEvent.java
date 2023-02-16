package com.maximde.speedrun.events;

import com.maximde.speedrun.Main;
import com.maximde.speedrun.commands.CommandTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathEvent extends PlayerListener {
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        CommandTimer.hour = 0;
        CommandTimer.sec = 0;
        CommandTimer.min = 0;
        CommandTimer.setOut(CommandTimer.hour, CommandTimer.min, CommandTimer.sec);
        Bukkit.getScheduler().cancelTask(CommandTimer.timer);
        Bukkit.broadcastMessage(Main.PREFIX+"§c"+event.getPlayer().getName()+" has died and the timer is now resetet! §6Time: "+CommandTimer.out);
    }
}
