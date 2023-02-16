package com.maximde.speedrun.events;

import com.maximde.speedrun.Main;
import com.maximde.speedrun.commands.CommandTimer;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class DragonDeathEvent extends EntityListener {
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof EnderDragon) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            if (entity.isDead())
                if (CommandTimer.sec > 0) {
                    Bukkit.broadcastMessage(Main.PREFIX+"§aRun complete!"+" §6Time: " + CommandTimer.out);
                    CommandTimer.hour = 0;
                    CommandTimer.sec = 0;
                    CommandTimer.min = 0;
                    Bukkit.getScheduler().cancelTask(CommandTimer.timer);
                }
        }

    }
}
