package com.maximde.speedrun.commands;

import com.maximde.speedrun.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage(Main.PREFIX+"§aCurrent time: §6"+CommandTimer.out);
        return false;
    }
}
