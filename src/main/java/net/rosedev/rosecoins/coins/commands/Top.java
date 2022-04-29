package net.rosedev.rosecoins.coins.commands;

import net.rosedev.rosecoins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Top implements Listener, CommandExecutor {

    private Main plugin;

    public Top(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)) {
            FileConfiguration config = plugin.getConfig();

            if((config.getString("Config.General.console_execute_commands") == "false")) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot execute commands in the console."));
                return false;
            }
        }else {
            if (args.length == 0) {
                FileConfiguration config = plugin.getConfig();
                Player player = (Player) sender;
                String Prefix = config.getString("Config.General.prefix");
                String BalanceMessage = config.getString("Messages.Coins.balance_check");
                FileConfiguration playerdata = plugin.getPlayerData();
                int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));

                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m---------------------------------------"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &71. &dItzPipeG &7- &f9000 coins"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &72. &dItzPipeG &7- &f8400 coins"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &73. &dItzPipeG &7- &f8000 coins"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your currently position: &d1"));
                //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m---------------------------------------"));
            }
        }
        return false;
    }
}
