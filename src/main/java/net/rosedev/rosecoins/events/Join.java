package net.rosedev.rosecoins.events;

import net.rosedev.rosecoins.Main;
import net.rosedev.rosecoins.api.RoseCoinsAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    private RoseCoinsAPI api;
    private Main plugin;

    public Join(Main plugin, RoseCoinsAPI api) {
        this.plugin = plugin;
        this.api = api;
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {
        FileConfiguration config = plugin.getConfig();

        FileConfiguration messagesEN = plugin.getMessagesEN();
        FileConfiguration messagesES = plugin.getMessagesES();

        Player player = event.getPlayer();
        FileConfiguration playerdata = plugin.getPlayerData();
        FileConfiguration challenges = plugin.getPlayerData();

        if(config.getString("Config.General.File.type").equals("MySQL")) {
            if (!this.api.hasProfile(event.getPlayer())) {
                this.api.createProfile(event.getPlayer().getUniqueId());
                if(config.getString("Config.General.Join.send_message_creating_file").equals("true")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        String CreatingMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.File.file_creating"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatingMessage));
                        String CreatedMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.File.file_created"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatedMessage));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        String CreatingMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.File.file_creating"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatingMessage));
                        String CreatedMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.File.file_created"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatedMessage));
                    }
                }
            }else {
                if(config.getString("Config.General.default_language").equals("EN")) {
                    String JoinMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Join.join_message"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage)
                            .replaceAll("%coins%", this.api.getCoins(player.getUniqueId().toString()) + "")
                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                    if(config.getString("Config.General.Actionbar.message_join").equals("true")) {
                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_join"));
                    }

                }else if(config.getString("Config.General.default_language").equals("ES")) {
                    String JoinMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Join.join_message"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage)
                            .replaceAll("%coins%", this.api.getCoins(player.getUniqueId().toString()) + "")
                            .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                    if(config.getString("Config.General.Actionbar.message_join").equals("true")) {
                        String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_join"));
                    }
                }
            }
        }else if(config.getString("Config.General.File.type").equals("FILE")) {
            if(!(playerdata.getString("Players."+player.getUniqueId()) == null)) {
                int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                if(config.getString("Config.General.Join.send_message_join") == "true") {

                    if(config.getString("Config.General.Join.send_message_creating_file").equals("true")) {
                        if(config.getString("Config.General.default_language").equals("EN")) {
                            String JoinMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.Join.join_message"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage)
                                    .replaceAll("%coins%", getCoins + "")
                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                            if(config.getString("Config.General.Actionbar.message_join").equals("true")) {
                                String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message_join"));
                            }

                        }else if(config.getString("Config.General.default_language").equals("ES")) {
                            String JoinMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.Join.join_message"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', JoinMessage)
                                    .replaceAll("%coins%", getCoins + "")
                                    .replaceAll("%symbol%", config.getString("Config.General.coin_symbol")));

                            if(config.getString("Config.General.Actionbar.message_join").equals("true")) {
                                String actionJoinMessage = ChatColor.translateAlternateColorCodes('&', config.getString("Config.General.Actionbar.actionbar_message"));
                            }
                        }
                    }
                }
            }else {
                String ConfigCoins = config.getString("Config.General.default_coins");
                playerdata.set("Players."+player.getUniqueId()+".name", player.getName());
                playerdata.set("Players."+player.getUniqueId()+".coins", ConfigCoins);
                plugin.savePlayerData();

                if(config.getString("Config.General.Join.send_message_join").equals("true")) {
                    if(config.getString("Config.General.default_language").equals("EN")) {
                        String CreatingMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.File.file_creating"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatingMessage));

                        String CreatedMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesEN.getString("Messages.File.file_created"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatedMessage));
                    }else if(config.getString("Config.General.default_language").equals("ES")) {
                        String CreatingMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.File.file_creating"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatingMessage));

                        String CreatedMessage = me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, messagesES.getString("Messages.File.file_created"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', CreatedMessage));
                    }
                }

            }
        }
    }
}
