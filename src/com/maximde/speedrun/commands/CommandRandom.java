package com.maximde.speedrun.commands;

import com.maximde.speedrun.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandRandom implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You are not a player!");
            return false;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("speedrun.random")) {
            p.sendMessage(Main.PREFIX+"§cNo permission!");
            return false;
        }
        if(args.length == 1) {
            switch(args[0]) {
                case "tnt":
                    Location loc1 = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(),p.getLocation().getZ());
                    Location loc2 = new Location(p.getLocation().getWorld(), p.getLocation().getX()+10, p.getLocation().getY() +10,p.getLocation().getZ() + 10);
                    for(Block block : select(loc1, loc2, p.getLocation().getWorld())) {
                        block.setTypeId(46);
                    }
                    break;
                case "border":
                    p.teleport(new Location(p.getLocation().getWorld(), 12550821,150,12550821));
                    break;
                case "name":
                    p.setPlayerListName("§6Notch");
                    break;
                case "sprint":
                    p.setSprinting(true);
                    break;
                case "sneak":
                    p.setSneaking(true);
                    break;
                case "level":
                    p.setLevel(100000);
                    break;
            }
        } else {
            p.sendMessage("§c/random tnt");
            p.sendMessage("§c/random border");
        }
        return false;
    }

    public static List<Block> select(Location loc1, Location loc2, World w) {
        int xMin, yMin, zMin, xMax, yMax, zMax;
        List<Block> blocks = new ArrayList<>();
        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();
        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();
        if (x1 > x2) {
            xMin = x2;
            xMax = x1;
        } else {
            xMin = x1;
            xMax = x2;
        }
        if (y1 > y2) {
            yMin = y2;
            yMax = y1;
        } else {
            yMin = y1;
            yMax = y2;
        }
        if (z1 > z2) {
            zMin = z2;
            zMax = z1;
        } else {
            zMin = z1;
            zMax = z2;
        }
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    Block b = (new Location(w, x, y, z)).getBlock();
                    blocks.add(b);
                }
            }
        }
        return blocks;
    }
}
