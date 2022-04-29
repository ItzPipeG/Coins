package net.rosedev.rosecoins.events;

import java.text.NumberFormat;

import net.rosedev.rosecoins.Main;
import net.rosedev.rosecoins.api.RoseCoinsAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillMobs implements Listener{

    private RoseCoinsAPI api;
    private Main plugin;

    public KillMobs(Main plugin, RoseCoinsAPI api) {
        this.plugin = plugin;
        this.api = api;
    }

    @EventHandler
    public void onKillMobs(EntityDeathEvent event){
        Entity entity = event.getEntity();
        Entity killer = event.getEntity().getKiller();
        FileConfiguration config = plugin.getConfig();
        FileConfiguration playerdata = plugin.getPlayerData();


        if (killer instanceof Player){
            if (entity.getType() == EntityType.SPIDER) {
                String mob = "SPIDER";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                    if (config.getString("Config.General.File.type").equals("MySQL")) {
                        Player player = event.getEntity().getKiller();
                        double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                        double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                        this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setMaximumFractionDigits(1);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));

                        }else if(config.getString("Config.General.File.type").equals("FILE")) {
                        Player player = event.getEntity().getKiller();
                        double getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                        double getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                        playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                        plugin.savePlayerData();
                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setMaximumFractionDigits(1);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                        }
                    }
                }
            }
        }

            }else if(entity.getType() == EntityType.ZOMBIE) {
                String mob = "ZOMBIE";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    double getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    double getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SKELETON) {
                String mob = "SKELETON";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.CAVE_SPIDER) {
                String mob = "CAVE_SPIDER";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                Player player = event.getEntity().getKiller();
                                int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                plugin.savePlayerData();
                                NumberFormat nf = NumberFormat.getInstance();
                                nf.setMaximumFractionDigits(1);
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.BLAZE) {
                String mob = "BLAZE";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.CREEPER) {
                String mob = "CREEPER";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.ENDER_DRAGON) {
                String mob = "ENDER_DRAGON";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.ENDERMAN) {
                String mob = "ENDERMAN";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.BAT) {
                String mob = "BAT";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.CHICKEN) {
                String mob = "CHICKEN";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.COW) {
                String mob = "COW";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.GHAST) {
                String mob = "GHAST";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.HORSE) {
                String mob = "HORSE";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.IRON_GOLEM) {
                String mob = "IRON_GOLEM";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.MAGMA_CUBE) {
                String mob = "MAGMA_CUBE";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.MUSHROOM_COW) {
                String mob = "MUSHROOM_COW";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.OCELOT) {
                String mob = "OCELOT";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.PIG) {
                String mob = "PIG";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.PIGLIN) {
                String mob = "PIG_ZOMBIE";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SHEEP) {
                String mob = "SHEEP";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SILVERFISH) {
                String mob = "SILVERFISH";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SLIME) {
                String mob = "SLIME";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SNOWMAN) {
                String mob = "SNOWMAN";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.SQUID) {
                String mob = "SQUID";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.VILLAGER) {
                String mob = "VILLAGER";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.WITCH) {
                String mob = "WITCH";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.WITHER) {
                String mob = "WITHER";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }else if(entity.getType() == EntityType.WOLF) {
                String mob = "WOLF";
                if(config.getString("Config.Rewards.Kill_Mobs."+mob) != null) {
                    if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") != null) {
                        if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"reward") != null) {
                            if(config.getString("Config.Rewards.Kill_Mobs."+mob+"."+"enabled") == "true") {
                                if(config.getString("Config.General.File.type").equals("MySQL")) {
                                    Player player = event.getEntity().getKiller();
                                    double getReward = config.getDouble("Config.Rewards.Kill_Mobs."+mob+".reward");
                                    double getCoins = this.api.getCoins(killer.getUniqueId().toString());

                                    this.api.setCoins(killer.getUniqueId().toString(), (getCoins+getReward));
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+" + nf.format(getReward) + "&a coins"));
                                }else if(config.getString("Config.General.File.type").equals("FILE")) {
                                    Player player = event.getEntity().getKiller();
                                    int getCoins = Integer.valueOf(playerdata.getString("Players."+player.getUniqueId()+".coins"));
                                    int getReward = Integer.valueOf(config.getString("Config.Rewards.Kill_Mobs."+mob+".reward"));
                                    playerdata.set("Players."+player.getUniqueId()+".coins", (getCoins+getReward));
                                    plugin.savePlayerData();
                                    NumberFormat nf = NumberFormat.getInstance();
                                    nf.setMaximumFractionDigits(1);
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a+"+nf.format(getReward)+"&a coins"));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
