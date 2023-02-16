package com.maximde.speedrun.commands;

import com.maximde.speedrun.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandTimer implements CommandExecutor {

    public static int holdTimer;

    public static String out = "00:00:00";

    public static int sec = 0;

    public static int min = 0;

    public static int hour = 0;

    public static int timer;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 1) {

            if(!(sender instanceof Player)) {
                sender.sendMessage("This command can only be executed by a player!");
                return false;
            }
            Player player = (Player) sender;

            switch (args[0]) {
                case "start":
                    if(min > 0 || hour > 0 || sec > 0) {
                        player.sendMessage(Main.PREFIX+"§cThe timer is already started! Use /resume or /reset");
                        return false;
                    }
                    timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("SpeedRun"),  new Runnable() {
                        public void run() {
                            counter();
                            setOut();
                            //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§l" + setOut()));
                        }
                    },0L, 20L);
                    break;
                case "pause":
                    player.sendMessage(Main.PREFIX+"§aPaused the Timer at: " + CommandTimer.out);
                    Bukkit.getScheduler().cancelTask(CommandTimer.timer);
                    break;
                case "resume":
                    player.sendMessage(Main.PREFIX+"§aResumed the Timer at: " + CommandTimer.out);
                    timer = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("SpeedRun"),  new Runnable() {
                        public void run() {
                            counter();
                            setOut();
                            //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§l" + setOut()));
                        }
                    },0L, 20L);
                    break;
            case "reset":
                CommandTimer.hour = 0;
                CommandTimer.sec = 0;
                CommandTimer.min = 0;
                setOut(CommandTimer.hour, CommandTimer.min, CommandTimer.sec);
                Bukkit.getScheduler().cancelTask(CommandTimer.timer);
                sender.sendMessage(Main.PREFIX+"§aTimer got resettet!");
                break;
            }
        } else {
            sender.sendMessage("§c--- Timer Commands ---");
            sender.sendMessage("§e /timer start");
            sender.sendMessage("§e /timer pause");
            sender.sendMessage("§e /timer resume");
            sender.sendMessage("§e /timer reset");
            sender.sendMessage("§c--- Timer Commands ---");
        }
        return false;
    }

    public void counter() {
        sec++;
        if (sec == 60) {
            sec = 0;
            min++;
        }
        if (min == 60) {
            min = 0;
            hour++;
        }
    }

    public String SetOutput(int sec, int min, int hour) {
        String out = "§b§l";
        if (hour >= 10 || hour <= -10) {
            out = out + hour;
        } else if (hour < 0) {
            out = out + "-0" + (hour * -1);
        } else {
            out = out + "0" + hour;
        }
        if (min >= 10 || min <= -10) {
            out = out + ":" + min;
        } else if (min < 0) {
            out = out + ":-0" + (min * -1);
        } else {
            out = out + ":0" + min;
        }
        if (sec >= 10 || sec <= -10) {
            out = out + ":" + sec;
        } else if (sec < 0) {
            out = out + ":-0" + (sec * -1);
        } else {
            out = out + ":0" + sec;
        }
        CommandTimer.out = out;
        return out;
    }

    public static void setOut(int hour, int min, int sec) {
        String out = "§b§l";
        if (hour >= 10 || hour <= -10) {
            out = out + hour;
        } else if (hour < 0) {
            out = out + "-0" + (hour * -1);
        } else {
            out = out + "0" + hour;
        }
        if (min >= 10 || min <= -10) {
            out = out + ":" + min;
        } else if (min < 0) {
            out = out + ":-0" + (min * -1);
        } else {
            out = out + ":0" + min;
        }
        if (sec >= 10 || sec <= -10) {
            out = out + ":" + sec;
        } else if (sec < 0) {
            out = out + ":-0" + (sec * -1);
        } else {
            out = out + ":0" + sec;
        }
        CommandTimer.out = out;
    }

    public String setOut() {
        out = "";
        if (hour >= 10 || hour <= -10) {
            out = out + hour;
        } else if (hour < 0) {
            out = out + "-0" + (hour * -1);
        } else {
            out = out + "0" + hour;
        }
        if (min >= 10 || min <= -10) {
            out = out + ":" + min;
        } else if (min < 0) {
            out = out + ":-0" + (min * -1);
        } else {
            out = out + ":0" + min;
        }
        if (sec >= 10 || sec <= -10) {
            out = out + ":" + sec;
        } else if (sec < 0) {
            out = out + ":-0" + (sec * -1);
        } else {
            out = out + ":0" + sec;
        }
        return out;
    }
}
