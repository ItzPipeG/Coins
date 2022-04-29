package net.rosedev.rosecoins.coins.commands;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.clip.placeholderapi.PlaceholderAPI;
import net.rosedev.rosecoins.Main;
import net.rosedev.rosecoins.api.RoseCoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Pay implements Listener, CommandExecutor{

    private RoseCoinsAPI api;
    private Main plugin;

    public Pay(Main plugin, RoseCoinsAPI api) {
        this.plugin = plugin;
        this.api = api;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {
        if(!(sender instanceof Player)) {
            FileConfiguration config = plugin.getConfig();

            if((config.getString("Config.General.console_execute_commands") == "false")) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot execute commands in the console."));
                return false;
            }
        }else {
            FileConfiguration config = plugin.getConfig();
            FileConfiguration messagesEN = plugin.getMessagesEN();
            FileConfiguration messagesES = plugin.getMessagesES();

            if (args.length == 0) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /pay <player> <coins>"));
            }else if(args.length == 1) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /pay <player> <coins>"));
            }else if (args.length > 1) {

                Pattern pattern = Pattern.compile("[a-zA-Z`~!@#$%^&*()_=+{}|;:,./<>?]");
                Matcher m = pattern.matcher(args[1]);
                if (m.find()) {
                    Player player = (Player) sender;
                    String PayPermission = config.getString("Config.General.Commands.Permissions.pay");
                    if(sender.hasPermission(PayPermission) || sender.hasPermission("rosecoins.admin")) {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            String CharactersErrorMessage = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.error_characters"));
                            String Prefix = config.getString("Config.General.prefix");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix+CharactersErrorMessage));
                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            String CharactersErrorMessage = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.error_characters"));
                            String Prefix = config.getString("Config.General.prefix");
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix+CharactersErrorMessage));
                        }

                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                    }
                }else {
                    if(config.getString("Config.General.Commands.Enabled.pay").equals("true")) {
                        String PayPermission = config.getString("Config.General.Commands.Permissions.pay");
                        if(sender.hasPermission(PayPermission) || sender.hasPermission("rosecoins.admin")) {
                            try{
                                Player player = (Player) sender;
                                Player target = Bukkit.getPlayer(args[0]);


                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    double getCoins = this.api.getCoins(player.getUniqueId().toString());
                                    double getString = Integer.valueOf(args[1]);
                                    double getCoinsTarget = this.api.getCoins(target.getUniqueId().toString());

                                    this.api.setCoins(player.getUniqueId().toString(), (getCoins-getString));
                                    this.api.setCoins(target.getUniqueId().toString(), (getCoinsTarget+getString));



                                    if((target == sender)) {
                                        if(config.getString("Config.General.default_language").equals("EN")) {
                                            String PayErrorYourself = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_error_yourself"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorYourself));
                                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                                            String PayErrorYourself = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_error_yourself"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorYourself));
                                        }
                                    }else {
                                        if(getString < 1) {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHey, you need use a number greater than 1."));
                                        }else if(getString > 0){
                                            if(getCoins >= getString) {
                                                if(config.getString("Config.General.default_language").equals("EN")) {
                                                    String PaySendedMessage = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_sended"));
                                                    String PayReceivedMessage = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_received"));

                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PaySendedMessage)
                                                            .replaceAll("%coins_target%", getString+"")
                                                            .replaceAll("%player_target%", target.getName()+"")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', PayReceivedMessage)
                                                            .replaceAll("%coins_target%", getString+"")
                                                            .replaceAll("%player_target%", target.getName()+"")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                                    if(config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                        NumberFormat nf = NumberFormat.getInstance();
                                                        nf.setMaximumFractionDigits(1);
                                                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                    }

                                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                    String PaySendedMessage = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_sended"));
                                                    String PayReceivedMessage = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_received"));

                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PaySendedMessage)
                                                            .replaceAll("%coins_target%", getString + "")
                                                            .replaceAll("%player_target%", target.getName() + "")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', PayReceivedMessage)
                                                            .replaceAll("%coins_target%", getString + "")
                                                            .replaceAll("%player_target%", target.getName() + "")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                                    if (config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                        NumberFormat nf = NumberFormat.getInstance();
                                                        nf.setMaximumFractionDigits(1);
                                                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                    }
                                                }
                                            }else {
                                                if(config.getString("Config.General.default_language").equals("EN")) {
                                                    String PayErrorEnough = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_error_enough"));
                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorEnough));
                                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                    String PayErrorEnough = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_error_enough"));
                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorEnough));
                                                }
                                            }
                                        }
                                    }



                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    FileConfiguration playerdata = plugin.getPlayerData();
                                    double getCoins = playerdata.getDouble("Players."+player.getUniqueId()+".coins");
                                    double getString = Double.valueOf(args[1]);
                                    double getCoinsTarget = playerdata.getDouble("Players."+target.getUniqueId()+".coins");

                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins-getString));
                                    playerdata.set("Players."+target.getUniqueId()+".coins", (getCoinsTarget+getString));
                                    plugin.savePlayerData();


                                    if((target == sender)) {
                                        if(config.getString("Config.General.default_language").equals("EN")) {
                                            String PayErrorYourself = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_error_yourself"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorYourself));
                                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                                            String PayErrorYourself = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_error_yourself"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorYourself));
                                        }
                                    }else {
                                        if(getString < 1) {
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHey, you need use a number greater than 1."));
                                        }else if(getString > 0){
                                            if(getCoins >= getString) {
                                                if(config.getString("Config.General.default_language").equals("EN")) {
                                                    String PaySendedMessage = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_sended"));
                                                    String PayReceivedMessage = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_received"));

                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PaySendedMessage)
                                                            .replaceAll("%coins_target%", getString+"")
                                                            .replaceAll("%player_target%", target.getName()+"")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', PayReceivedMessage)
                                                            .replaceAll("%coins_target%", getString+"")
                                                            .replaceAll("%player_target%", target.getName()+"")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                                    if(config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                        NumberFormat nf = NumberFormat.getInstance();
                                                        nf.setMaximumFractionDigits(1);
                                                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                    }

                                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                    String PaySendedMessage = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_sended"));
                                                    String PayReceivedMessage = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_received"));

                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PaySendedMessage)
                                                            .replaceAll("%coins_target%", getString + "")
                                                            .replaceAll("%player_target%", target.getName() + "")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', PayReceivedMessage)
                                                            .replaceAll("%coins_target%", getString + "")
                                                            .replaceAll("%player_target%", target.getName() + "")
                                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                                    if (config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                        NumberFormat nf = NumberFormat.getInstance();
                                                        nf.setMaximumFractionDigits(1);
                                                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                    }
                                                }
                                            }else {
                                                if(config.getString("Config.General.default_language").equals("EN")) {
                                                    String PayErrorEnough = PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.pay_error_enough"));
                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorEnough));
                                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                    String PayErrorEnough = PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.pay_error_enough"));
                                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PayErrorEnough));
                                                }
                                            }
                                        }
                                    }
                                }
                            }catch(NullPointerException e){
                                Player player = (Player) sender;
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist in the database system."));
                            }


                        }else {
                            Player player = (Player) sender;
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                        }
                    }else {
                        Player player = (Player) sender;
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, this command is disabled for a admin!"));
                    }
                }
            }
        }
        return false;
    }
}
