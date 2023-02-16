package com.maximde.speedrun.events;

import com.maximde.speedrun.Main;
import com.maximde.speedrun.commands.CommandTimer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class JoinEvent extends PlayerListener {
    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Main.PREFIX+"§a"+player.getName()+" joined the server");
        if(CommandTimer.min > 0 || CommandTimer.hour > 0 || CommandTimer.sec > 0) {
            player.sendMessage(Main.PREFIX+"§cThe timer is paused!");
        }
    }

}
