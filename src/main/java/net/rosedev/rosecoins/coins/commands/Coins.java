package net.rosedev.rosecoins.coins.commands;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class Coins implements Listener, CommandExecutor {


    private RoseCoinsAPI api;
    private Main plugin;

    public Coins(Main plugin, RoseCoinsAPI api) {
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
            FileConfiguration config = plugin.getConfig();
            FileConfiguration messagesEN = plugin.getMessagesEN();
            FileConfiguration messagesES = plugin.getMessagesES();

            if (args.length == 0) {
                Player player = (Player) sender;
                FileConfiguration playerdata = plugin.getPlayerData();

                if(config.getString("Config.General.default_language").equals("EN")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins check <player> &7- &fCheck the coins of a player."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins add <player> <coins> &7- &fAdd coins to a player."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins remove <player> <coins> &7- &fRemove coins to a player."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins set <player> <coins> &7- &fSet coins to a player."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reset <player> &7- &fReset coins to a player."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reload &7- &fReloaded config file."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                }else if(config.getString("Config.General.default_language").equals("ES")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins check <jugador> &7- &fChequear las monedas de un jugador."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins add <jugador> <monedas> &7- &fAgregar monedas a un jugador."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins remove <jugador> <monedas> &7- &fQuitar monedas a un jugador."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins set <jugador> <monedas> &7- &fPoner una cantidad de monedas a un jugador."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reset <jugador> &7- &fBorrar las monedas de un jugador."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reload &7- &fReiniciar el archivo de configuración."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                }

            }else if(args.length == 1) {
                Player player = (Player) sender;
                if(args[0].equalsIgnoreCase("set")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins set <jugador> <monedas>"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins set <jugador> <monedas>"));
                    }
                }else if(args[0].equalsIgnoreCase("reset")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins reset <jugador>"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins reset <jugador>"));
                    }
                }else if(args[0].equalsIgnoreCase("check")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins check <jugador>"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins check <jugador>"));
                    }
                }else if(args[0].equalsIgnoreCase("reload")) {
                    if(sender.hasPermission("rosecoins.command.reload") || sender.hasPermission("rosecoins.admin")) {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            plugin.reloadConfig();
                            String ReloadFile = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.File.file_reloaded"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ReloadFile));
                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            plugin.reloadConfig();
                            String ReloadFile = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.File.file_reloaded"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ReloadFile));
                        }
                    }else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                    }
                }else if(args[0].equalsIgnoreCase("add")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins add <jugador> <monedas>"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins add <jugador> <monedas>"));
                    }

                }else if(args[0].equalsIgnoreCase("remove")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins remove <jugador> <monedas>"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins remove <jugador> <monedas>"));
                    }
                }else {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins check <player> &7- &fCheck the coins of a player."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins add <player> <coins> &7- &fAdd coins to a player."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins remove <player> <coins> &7- &fRemove coins to a player."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins set <player> <coins> &7- &fSet coins to a player."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reset <player> &7- &fReset coins to a player."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reload &7- &fReloaded config file."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins check <jugador> &7- &fChequear las monedas de un jugador."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins add <jugador> <monedas> &7- &fAgregar monedas a un jugador."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins remove <jugador> <monedas> &7- &fQuitar monedas a un jugador."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins set <jugador> <monedas> &7- &fPoner una cantidad de monedas a un jugador."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reset <jugador> &7- &fBorrar las monedas de un jugador."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d/coins reload &7- &fReiniciar el archivo de configuración."));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
                    }

                }
            }else if(args.length > 1) {
                Player player = (Player) sender;




                if(args[0].equalsIgnoreCase("set") && args.length > 1) {
                    String SetPermission = config.getString("Config.General.Commands.Permissions.set");
                    if(config.getString("Config.General.Commands.Enabled.set") == "true") {
                        try {
                            Pattern pattern = Pattern.compile("[a-zA-Z`~!@#$%^&*()_=+{}|;:,./<>?]");
                            Matcher m = pattern.matcher(args[2]);
                            if (m.find()) {
                                if(sender.hasPermission(SetPermission) && sender.hasPermission("rosecoins.admin")) {
                                    if(config.getString("Config.General.default_language").equals("EN")) {
                                        String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.error_characters"));
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                                        String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.error_characters"));
                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                    }
                                }else {
                                    String Prefix = config.getString("Config.General.prefix");
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix+"&cSorry, You no have permissions to use this command."));
                                }
                            }else {
                                if(sender.hasPermission(SetPermission) || sender.hasPermission("rosecoins.admin")) {
                                    if(config.getString("Config.General.File.type").equals("MySQL")) {
                                        NumberFormat nf = NumberFormat.getInstance();
                                        nf.setMaximumFractionDigits(1);
                                        Player target = Bukkit.getPlayer(args[1]);
                                        double Coins = Double.parseDouble(args[2]);
                                        this.api.setCoins(target.getUniqueId().toString(), Coins);

                                        if(config.getString("Config.General.default_language").equals("EN")) {
                                            String SetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_seted"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SetedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName() + "")
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                                            String SetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_seted"));
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', SetedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName() + "")
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                        }
                                    }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                        try{
                                            FileConfiguration playerdata = plugin.getPlayerData();
                                            Player target = Bukkit.getPlayer(args[1]);
                                            double Coins = Double.parseDouble(args[2]);
                                            if(playerdata.getString("Players."+target.getUniqueId()+".name") != null){
                                                playerdata.set("Players."+player.getUniqueId()+".name", target.getName());
                                            }
                                            playerdata.set("Players."+target.getUniqueId()+".coins", Coins);

                                            if(config.getString("Config.General.default_language").equals("EN")) {
                                                String SetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_seted"));
                                                double getCoinsTarget = Double.valueOf(playerdata.getDouble("Players."+target.getUniqueId()+".coins"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SetedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getCoinsTarget))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();
                                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                String SetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_seted"));
                                                double getCoinsTarget = Double.valueOf(playerdata.getDouble("Players."+target.getUniqueId()+".coins"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', SetedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getCoinsTarget))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();
                                            }
                                        }catch(NullPointerException e){
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist in the database system."));
                                            return false;
                                        }
                                    }
                                }else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                                }
                            }
                        }catch (ArrayIndexOutOfBoundsException e) {
                            if(config.getString("Config.General.default_language").equals("EN")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins set <jugador> <monedas>"));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins set <jugador> <monedas>"));
                            }
                            return false;
                        }
                    }else {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.command_disabled_message"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.command_disabled_message"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                        }
                    }




                }else if(args[0].equalsIgnoreCase("check") && args.length > 1) {
                    try{
                        String CheckPermission = config.getString("Config.General.Commands.Permissions.check");
                        if(config.getString("Config.General.Commands.Enabled.check") == "true") {
                            if(sender.hasPermission(CheckPermission) || sender.hasPermission("rosecoins.admin")) {
                                Player target = Bukkit.getPlayerExact(args[1]);
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String CheckCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_check"));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CheckCoins)
                                            .replaceAll("%coins_target%", nf.format(this.api.getCoins(target.getUniqueId().toString()))+"")
                                            .replaceAll("%player_target%", target.getName())
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String CheckCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_check"));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CheckCoins)
                                            .replaceAll("%coins_target%", nf.format(this.api.getCoins(target.getUniqueId().toString()))+"")
                                            .replaceAll("%player_target%", target.getName())
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }
                            }else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                            }
                        }else {
                            if(config.getString("Config.General.default_language").equals("EN")) {
                                String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.command_disabled_message"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.command_disabled_message"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                            }
                        }
                    }catch (NullPointerException e) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist in the database system."));
                        return false;
                    }




                }else if(args[0].equalsIgnoreCase("reset") && args.length > 1) {
                    try{
                        String ResetPermission = config.getString("Config.General.Commands.Permissions.reset");
                        if(config.getString("Config.General.Commands.Enabled.reset") == "true") {
                            if(sender.hasPermission(ResetPermission) || sender.hasPermission("rosecoins.admin")) {
                                if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    FileConfiguration playerdata = plugin.getPlayerData();
                                    int coins = config.getInt("Config.General.default_coins");

                                    playerdata.set("Players."+target.getUniqueId().toString()+".coins", coins);
                                    plugin.savePlayerData();
                                }else{
                                    Player target = Bukkit.getPlayer(args[1]);
                                    this.api.setCoins(target.getUniqueId().toString(), config.getDouble("Config.General.default_coins"));
                                }

                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    String ResetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_reseted"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ResetedCoins)
                                            .replaceAll("%player_target%", target.getName())
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    String ResetedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_reseted"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ResetedCoins)
                                            .replaceAll("%player_target%", target.getName())
                                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                }
                            }else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                            }
                        }else {
                            if(config.getString("Config.General.default_language").equals("EN")) {
                                String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.command_disabled_message"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.command_disabled_message"));
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                            }
                        }
                    }catch (NullPointerException e) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist in the database system."));
                        return false;
                    }




                }else if(args[0].equalsIgnoreCase("add") && args.length > 1) {
                    try {
                        Pattern pattern = Pattern.compile("[a-zA-Z`~!@#$%^&*()_=+{}|;:,./<>?]");
                        Matcher m = pattern.matcher(args[2]);
                        String AddPermission = config.getString("Config.General.Commands.Permissions.add");
                        if (m.find()) {
                            if(sender.hasPermission(AddPermission) && sender.hasPermission("rosecoins.admin")) {
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.error_characters"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.error_characters"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                }
                            }else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                            }
                        }else {
                            if(config.getString("Config.General.Commands.Enabled.add") == "true") {
                                if(sender.hasPermission(AddPermission) || sender.hasPermission("rosecoins.admin")) {
                                    if(config.getString("Config.General.File.type").equals("MySQL")) {

                                        Player target = Bukkit.getPlayer(args[1]);
                                        double Coins = Double.parseDouble(args[2]);
                                        this.api.addCoins(target.getUniqueId().toString(), Coins);

                                        if(config.getString("Config.General.default_language").equals("EN")) {
                                            String AddedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_added"));
                                            NumberFormat nf = NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(1);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AddedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName() + "")
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                                            String AddedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_added"));
                                            NumberFormat nf = NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(1);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AddedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName() + "")
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                                        }

                                    }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                        Player target = Bukkit.getPlayer(args[1]);
                                        try{
                                            FileConfiguration playerdata = plugin.getPlayerData();
                                            double getCoins = Double.parseDouble(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                            double getString = Double.parseDouble(args[2]);
                                            double getCoinsTarget = Double.parseDouble(playerdata.getString("Players."+target.getUniqueId()+".coins"));

                                            playerdata.set("Players."+target.getUniqueId()+".coins", (getCoinsTarget+getString));

                                            if(config.getString("Config.General.default_language").equals("EN")) {
                                                String AddedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_added"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AddedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getString))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();

                                                if(config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                    String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                }

                                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                String AddedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_added"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AddedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getString))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();

                                                if(config.getString("Config.General.Actionbar.message_coins_received").equals("true")) {
                                                    String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_received"));
                                                }

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
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.command_disabled_message"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.command_disabled_message"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                                }
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException e) {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins add <jugador> <monedas>"));
                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins add <jugador> <monedas>"));
                        }
                        return false;
                    }




                }else if(args[0].equalsIgnoreCase("remove") && args.length > 1) {
                    try {
                        Pattern pattern = Pattern.compile("[a-zA-Z`~!@#$%^&*()_=+{}|;:,./<>?]");
                        Matcher m = pattern.matcher(args[2]);
                        String RemovePermission = config.getString("Config.General.Commands.Permissions.remove");
                        if (m.find()) {
                            if(sender.hasPermission(RemovePermission) && sender.hasPermission("rosecoins.admin")) {
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.error_characters"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String CharactersErrorMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.error_characters"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CharactersErrorMessage));
                                }
                            }else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                            }
                        }else {
                            if(config.getString("Config.General.Commands.Enabled.remove") == "true") {
                                if(sender.hasPermission(RemovePermission) || sender.hasPermission("rosecoins.admin")) {
                                    if(config.getString("Config.General.File.type").equals("MySQL")) {

                                        Player target = Bukkit.getPlayer(args[1]);
                                        double Coins = Double.parseDouble(args[2]);
                                        this.api.removeCoins(target.getUniqueId().toString(), Coins);

                                        if(config.getString("Config.General.default_language").equals("EN")) {
                                            String RemovedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_removed"));
                                            NumberFormat nf = NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(1);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', RemovedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName())
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                                            String RemovedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_removed"));
                                            NumberFormat nf = NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(1);
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', RemovedCoins)
                                                    .replaceAll("%coins_target%", nf.format(Coins))
                                                    .replaceAll("%player_target%", target.getName())
                                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                        }


                                    }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                        try{
                                            Player target = Bukkit.getPlayer(args[1]);
                                            FileConfiguration playerdata = plugin.getPlayerData();
                                            double getCoins = Double.parseDouble(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                            double getString = Double.parseDouble(args[2]);
                                            double getCoinsTarget = Double.parseDouble(playerdata.getString("Players."+target.getUniqueId()+".coins"));

                                            playerdata.set("Players."+target.getUniqueId()+".coins", (getCoinsTarget-getString));


                                            if(config.getString("Config.General.default_language").equals("EN")) {
                                                String RemovedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.coins_removed"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', RemovedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getString))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();
                                            }else if(config.getString("Config.General.default_language").equals("ES")) {
                                                String RemovedCoins = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.coins_removed"));
                                                NumberFormat nf = NumberFormat.getInstance();
                                                nf.setMaximumFractionDigits(1);
                                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', RemovedCoins)
                                                        .replaceAll("%coins_target%", nf.format(getString))
                                                        .replaceAll("%player_target%", target.getName() + "")
                                                        .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));
                                                plugin.savePlayerData();
                                            }
                                        }catch(NullPointerException e){
                                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&CError, the player "+args[1]+" &cdoes not exist in the playerdata file."));
                                            return false;
                                        }
                                    }
                                }else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, You no have permissions to use this command."));
                                }
                            }else {
                                if(config.getString("Config.General.default_language").equals("EN")) {
                                    String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Coins.command_disabled_message"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                                }else if(config.getString("Config.General.default_language").equals("ES")) {
                                    String CommandDisabledError = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Coins.command_disabled_message"));
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', CommandDisabledError));
                                }
                            }
                        }
                    }catch (ArrayIndexOutOfBoundsException e) {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCorrect usage: /coins remove <jugador> <monedas>"));
                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUso correcto: /coins remove <jugador> <monedas>"));
                        }
                        return false;
                    }
                }
            }
        }

        return false;
    }
}
