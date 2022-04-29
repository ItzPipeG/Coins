package net.rosedev.rosecoins.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.UUID;

import net.rosedev.rosecoins.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RoseCoinsAPI {

    private Main plugin;

    public RoseCoinsAPI(Main plugin) {
        this.plugin = plugin;
    }

    public double getCoins(String uuid) {
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.General.File.type").equals("MySQL")) {
            try {
                PreparedStatement st = this.plugin.getConnection().prepareStatement("SELECT * FROM coins WHERE UUID = ?");
                st.setString(1, uuid);
                ResultSet rs = st.executeQuery();
                if (rs.next())
                    return rs.getDouble("coins");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(config.getString("Config.General.File.type").equals("FILE")) {
            try{
                FileConfiguration playerdata = plugin.getPlayerData();
                return playerdata.getDouble("Players."+uuid+".coins");
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void createProfile(UUID uuid) {
        try {
            PreparedStatement st = this.plugin.getConnection().prepareStatement("INSERT INTO coins (UUID,coins) VALUES (?,?)");
            FileConfiguration config = plugin.getConfig();
            st.setString(1, String.valueOf(uuid));
            st.setDouble(2, config.getDouble("Config.General.default_coins"));
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasProfile(Player player) {
        try {
            PreparedStatement e = this.plugin.getConnection().prepareStatement("SELECT * FROM coins WHERE UUID = ('" + player.getUniqueId() + "');");
            ResultSet results = e.executeQuery();
            return results.next();
        } catch (SQLException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public void setCoins(String uuid, double coins) {
        FileConfiguration config = plugin.getConfig();
        if(config.getString("Config.General.File.type").equals("MySQL")) {
            if (getCoins(uuid) == -1) {
                try {
                    PreparedStatement st = this.plugin.getConnection().prepareStatement("INSERT INTO coins (UUID,coins) VALUES = ?,?");
                    st.setDouble(2, coins);
                    st.setString(1, uuid);
                    st.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement st = this.plugin.getConnection()    .prepareStatement("UPDATE coins SET coins = ? WHERE UUID = ?");
                    st.setString(2, uuid);
                    st.setDouble(1, coins);
                    st.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addCoins(String uuid, double coins) {
        double current = getCoins(uuid);
        setCoins(uuid, coins + current);
    }

    public void removeCoins(String uuid, double coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }
}
