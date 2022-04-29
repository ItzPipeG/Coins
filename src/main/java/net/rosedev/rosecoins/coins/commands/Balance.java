package net.rosedev.rosecoins.coins.commands;

import java.text.NumberFormat;

import net.rosedev.rosecoins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.rosedev.rosecoins.api.RoseCoinsAPI;

public class Balance implements Listener, CommandExecutor {


    private RoseCoinsAPI api;
    private Main plugin;

    public Balance(Main plugin, RoseCoinsAPI api) {
        this.plugin = plugin;
        this.api = api;
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
                FileConfiguration messagesEN = plugin.getMessagesEN();
                FileConfiguration messagesES = plugin.getMessagesES();
                if(config.getString("Config.General.Commands.Enabled.balance").equals("true")) {
                    Player player = (Player) sender;
                    String BalancePermission = config.getString("Config.General.Commands.Permissions.balance");
                    if(sender.hasPermission(BalancePermission) || sender.hasPermission("rosecoins.admin")) {
                        if(config.getString("Config.General.File.type").equals("MySQL")) {

                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setMaximumFractionDigits(1);

                            if(config.getString("Config.General.default_language").equals("EN")) {
                                String BalanceMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.balance_check"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceMessage)
                                        .replaceAll("%coins%", nf.format(this.api.getCoins(player.getUniqueId().toString())))
                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                String BalanceMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.balance_check"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceMessage)
                                        .replaceAll("%coins%", nf.format(this.api.getCoins(player.getUniqueId().toString())))
                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                            }

                        }else if(config.getString("Config.General.File.type").equals("FILE")) {
                            FileConfiguration playerdata = plugin.getPlayerData();
                            double getCoins = Double.valueOf(playerdata.getDouble("Players."+player.getUniqueId()+".coins"));

                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setMaximumFractionDigits(1);

                            if(config.getString("Config.General.default_language").equals("EN")) {
                                String BalanceMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.balance_check"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceMessage)
                                        .replaceAll("%coins%", nf.format(getCoins))
                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                String BalanceMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.balance_check"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceMessage)
                                        .replaceAll("%coins%", nf.format(getCoins))
                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                            }
                        }
                    }else {
                        String Prefix = config.getString("Config.General.prefix");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix+"&cSorry, You no have permissions to use this command."));
                    }
                }else {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, this command is disabled for a admin!"));
                }
            }else if(args.length == 1) {
                FileConfiguration config = plugin.getConfig();
                FileConfiguration messagesEN = plugin.getMessagesEN();
                FileConfiguration messagesES = plugin.getMessagesES();

                if(config.getString("Config.General.Commands.Enabled.balance").equals("true")) {
                    Player player = (Player) sender;
                    String BalancePermission = config.getString("Config.General.Commands.Permissions.balance_others");
                    if(sender.hasPermission(BalancePermission) || sender.hasPermission("rosecoins.admin")) {
                        if(config.getString("Config.General.File.type").equals("MySQL")) {

                            NumberFormat nf = NumberFormat.getInstance();
                            nf.setMaximumFractionDigits(1);
                            Player target = Bukkit.getPlayer(args[0]);

                            if(config.getString("Config.General.default_language").equals("EN")) {
                                String BalanceOthersMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.balance_check_others"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceOthersMessage)
                                        .replaceAll("%coins_target%", nf.format(this.api.getCoins(target.getUniqueId().toString()))
                                                .replaceAll("%player_target%", target.getName())
                                                .replaceAll("%symbol%", config.getString("Config.General.coin_symbol"))));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                String BalanceOthersMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.balance_check_others"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceOthersMessage)
                                        .replaceAll("%coins_target%", nf.format(this.api.getCoins(target.getUniqueId().toString()))
                                                .replaceAll("%player_target%", target.getName())
                                                .replaceAll("%symbol%", config.getString("Config.General.coin_symbol"))));
                            }



                        }else if(config.getString("Config.General.File.type").equals("FILE")) {
                            Player target = Bukkit.getPlayer(args[0]);
                            try{
                                FileConfiguration playerdata = plugin.getPlayerData();
                                double getCoins = Double.valueOf(playerdata.getDouble("Players."+target.getUniqueId()+".coins"));

                                NumberFormat nf = NumberFormat.getInstance();
                                nf.setMaximumFractionDigits(1);
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String BalanceOthersMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.balance_check_others"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceOthersMessage)
                                            .replaceAll("%coins_target%", nf.format(getCoins)+"")
                                            .replaceAll("%player_target%", target.getName()+"")
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String BalanceOthersMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.balance_check_others"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', BalanceOthersMessage)
                                            .replaceAll("%coins_target%", nf.format(getCoins)+"")
                                            .replaceAll("%player_target%", target.getName()+"")
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }

                            }catch(NullPointerException e){
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist in the database system."));
                                return false;
                            }
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                    }
                }else {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, this command is disabled for a admin!"));
                }
            }
        }
        return false;
    }
}
