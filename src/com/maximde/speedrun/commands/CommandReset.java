package com.maximde.speedrun.commands;

import com.maximde.speedrun.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class CommandReset implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(!p.hasPermission("speedrun.reset")) {
            p.sendMessage(Main.PREFIX+"§cNo permission!");
            return false;
        }

        p.kickPlayer("§6Speedruns >> " +
                "§aReseting worlds! rejoin in a few seconds"+
                " §7Hosted by Maxim");

        try {
            recreateWorld("world");
            recreateWorld("world_nether");
            recreateWorld("world_the_end");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Main.INSTANCE.getServer().shutdown();
        return false;
    }

    public void recreateWorld(String worldname) {
        deleteWorldFolder(worldname);
    }

    private static void deleteDirectoryWalkTree(Path path) throws IOException {
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null)
                    throw exc;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(path, visitor);
    }

    public static void deleteWorldFolder(String world) {
        try {
            Path path = Paths.get("/root/mc/SpeedRuns/1.0/"+world);
            deleteDirectoryWalkTree(path);
        } catch (IOException ioException) {
            System.out.println("Couldn't delete world folder!");
        }
    }
}
