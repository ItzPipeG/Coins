package net.rosedev.rosecoins.events;

import net.rosedev.rosecoins.Main;
import net.rosedev.rosecoins.api.RoseCoinsAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.text.NumberFormat;

public class CoinsXP implements Listener {

    private RoseCoinsAPI api;
    private Main plugin;

    public CoinsXP(Main plugin, RoseCoinsAPI api) {
        this.plugin = plugin;
        this.api = api;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration playerdata = plugin.getPlayerData();
        FileConfiguration config = plugin.getConfig();

        if(config.getString("Config.General.File.type").equals("MySQL")) {
            if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                player.setLevel(0);
                player.setLevel((int) this.api.getCoins(player.getUniqueId().toString()));
            }

        }else if(config.getString("Config.General.File.type").equals("FILE")) {
            if(!(playerdata.getString("Players."+player.getUniqueId()) == null)) {
                if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                    player.setLevel(0);
                    double getCoins = Double.valueOf(playerdata.getDouble("Players."+player.getUniqueId()+".coins"));
                    player.setLevel(Integer.valueOf((int) getCoins));
                }
            }
        }
    }

    @EventHandler
    public void onReceiveXP(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        FileConfiguration playerdata = plugin.getPlayerData();

        if(config.getString("Config.General.File.type").equals("MySQL")) {
            if(this.api.hasProfile(event.getPlayer())){
                if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                    player.setLevel(0);
                    player.setLevel((int) this.api.getCoins(player.getUniqueId().toString()));
                }
            }

        }else if(config.getString("Config.General.File.type").equals("FILE")) {
            if(!(playerdata.getString("Players."+player.getUniqueId()) == null)) {
                if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                    player.setLevel(0);
                    double getCoins = Double.valueOf(playerdata.getDouble("Players."+player.getUniqueId()+".coins"));
                    player.setLevel(Integer.valueOf((int) getCoins));
                }
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        FileConfiguration playerdata = plugin.getPlayerData();

        if(config.getString("Config.General.File.type").equals("MySQL")) {
            if(this.api.hasProfile(event.getPlayer())){
                if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                    player.setLevel(0);
                    player.setLevel((int) this.api.getCoins(player.getUniqueId().toString()));
                }
            }

        }else if(config.getString("Config.General.File.type").equals("FILE")) {
            if(!(playerdata.getString("Players."+player.getUniqueId()) == null)) {
                if(config.getString("Config.General.Join.level_equal_to_coins") == "true") {
                    player.setLevel(0);
                    double getCoins = Double.valueOf(playerdata.getDouble("Players."+player.getUniqueId()+".coins"));
                    player.setLevel(Integer.valueOf((int) getCoins));
                }
            }
        }
    }
}
