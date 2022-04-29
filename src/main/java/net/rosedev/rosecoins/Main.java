package net.rosedev.rosecoins;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.rosedev.rosecoins.api.RoseCoinsAPI;
import net.rosedev.rosecoins.events.KillMobs;
import net.rosedev.rosecoins.coins.commands.Balance;
import net.rosedev.rosecoins.coins.commands.Coins;
import net.rosedev.rosecoins.coins.commands.Pay;
import net.rosedev.rosecoins.events.CoinsXP;
import net.rosedev.rosecoins.events.Join;
import net.rosedev.rosecoins.placeholderapi.PlaceholderAPI;

public class Main extends JavaPlugin {

    FileConfiguration config = this.getConfig();
    PluginDescriptionFile description = getDescription();
    public String rutaConfig;
    public String version = description.getVersion();
    private FileConfiguration playerdata = null;
    private FileConfiguration database2 = null;
    private FileConfiguration challenges = null;
    private FileConfiguration messagesEN = null;
    private FileConfiguration messagesES = null;
    private File playerdataFile = null;
    private File databaseFile = null;
    private File challengesFile = null;
    private File messagesENFile = null;
    private File messagesESFile = null;
    private RoseCoinsAPI CoinsAPI = new RoseCoinsAPI(this);

    private Connection connection;
    public String host, database, username, password;
    public int port;

    public void onEnable(){
        createFolderPlayerdata();
        createFolderLanguages();
        registerEvents();
        registerConfig();
        registerCommands();
        registerPlayerData();
        registerMessagesEN();
        registerMessagesES();
        registerDatabase();
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+&7&m---------------------------&7+"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&dRoseCoins &7| &fv"+version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fStatus: &aEnabled"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fAuthor: &dItzPipeG"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fVersion: &dv"+version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+&7&m---------------------------&7+"));
        if(config.getString("Config.General.File.type").equals("MySQL")) {
            MySQLSetup();
        }
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPI(this, this.CoinsAPI).register();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccesfully enabled the PlaceholderAPI compatibility."));
        }else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError, not found the PlaceholderAPI plugin."));
        }
    }

    //Comandos al desactivarse el plugin

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+&7&m---------------------------&7+"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&dRoseCoins &7| &fv"+version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fStatus: &cDisabled"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fAuthor: &dItzPipeG"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+ &fVersion: &dv"+version));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7+&7&m---------------------------&7+"));
    }

    //MySQL

    public void MySQLSetup() {
        try {
            synchronized (this) {
                if(getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
                host = this.getDatabase2().getString("Database.MySQL.host");
                port = Integer.valueOf(this.getDatabase2().getString("Database.MySQL.port"));
                database = this.getDatabase2().getString("Database.MySQL.database");
                username = this.getDatabase2().getString("Database.MySQL.username");
                password = this.getDatabase2().getString("Database.MySQL.password");

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":"
                        + this.port + "/" + this.database, this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccesfully, The MySQL is now connected."));
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));

                try {
                    String profile = "CREATE TABLE IF NOT EXISTS coins (UUID VARCHAR(100), coins INT(16))";
                    PreparedStatement statement = this.connection.prepareStatement(profile);
                    statement.executeUpdate();
                } catch (SQLException sql) {
                    sql.printStackTrace();
                }
            }
        }catch(SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError in the connection with MySQL."));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe server you are trying to connect to is down"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cOr check the data in database.yml."));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));
            this.getPluginLoader().disablePlugin(this);
        }catch(ClassNotFoundException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError in the connection with MySQL."));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r"));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlease, check the data in database.yml."));
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m---------------------------"));
            this.getPluginLoader().disablePlugin(this);
        }
    }


    public Connection getConnection() {
        return connection;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //MySQL File

    public FileConfiguration getDatabase2(){
        if(database2 == null){
            reloadDatabase();
        }
        return database2;
    }

    public void reloadDatabase(){
        if(database2 == null){
            databaseFile = new File(getDataFolder(), "database.yml");
        }
        database2 = YamlConfiguration.loadConfiguration(databaseFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("database.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                database2.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void saveDatabase(){
        try{
            database2.save(databaseFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerDatabase(){
        databaseFile = new File(this.getDataFolder(), "database.yml");
        if(!databaseFile.exists()){
            this.getDatabase2().options().copyDefaults(true);
            saveDatabase();
        }
    }

    //Registrar todos los eventos activos

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Join(this, this.CoinsAPI), this);
        pm.registerEvents(new KillMobs(this, this.CoinsAPI), this);
        pm.registerEvents(new CoinsXP(this, this.CoinsAPI),this);
    }

    //Registrar todos los comandos activos

    public void registerCommands() {
        this.getCommand("coins").setExecutor(new Coins(this, this.CoinsAPI));
        this.getCommand("pay").setExecutor(new Pay(this, this.CoinsAPI));
        this.getCommand("balance").setExecutor(new Balance(this, this.CoinsAPI));
    }


    //Crear archivo de configuración

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }


    //Crear archivo de playerdata

    public FileConfiguration getPlayerData(){
        if(playerdata == null){
            reloadPlayerData();
        }
        return playerdata;
    }

    public void reloadPlayerData(){
        if(playerdata == null){
            playerdataFile = new File(getDataFolder() + File.separator + "playerdata", "playerdata.yml");
        }
        playerdata = YamlConfiguration.loadConfiguration(playerdataFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("playerdata.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                playerdata.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void savePlayerData(){
        try{
            playerdata.save(playerdataFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerPlayerData(){
        playerdataFile = new File(this.getDataFolder(), "playerdata.yml");
        if(!playerdataFile.exists()){
            this.getPlayerData().options().copyDefaults(true);
            savePlayerData();
        }
    }

    // Crear carpeta Playerdata

    public void createFolderPlayerdata() {
        File folder = new File(getDataFolder(), "playerdata");
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    // Crear carpeta Languages

    public void createFolderLanguages() {
        File folder = new File(getDataFolder(), "languages");
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    // Crear archivo de Messages_EN

    public FileConfiguration getMessagesEN(){
        if(messagesEN == null){
            reloadMessagesEN();
        }
        return messagesEN;
    }

    public void reloadMessagesEN(){
        if(messagesEN == null){
            messagesENFile = new File(getDataFolder() + File.separator + "languages", "messages_en.yml");
        }
        messagesEN = YamlConfiguration.loadConfiguration(messagesENFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("messages_en.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messagesEN.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void saveMessagesEN(){
        try{
            messagesEN.save(messagesENFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessagesEN(){
        File folder = new File(getDataFolder(), "languages");
        File messagesENFile = new File(folder, "messages_en.yml");
        if(!messagesENFile.exists()){
            this.getMessagesEN().options().copyDefaults(true);
            saveMessagesEN();
        }
    }


    // Crear archivo de Messages_ES

    public FileConfiguration getMessagesES(){
        if(messagesES == null){
            reloadMessagesES();
        }
        return messagesES;
    }

    public void reloadMessagesES(){
        if(messagesES == null){
            messagesESFile = new File(getDataFolder() + File.separator + "languages", "messages_es.yml");
        }
        messagesES = YamlConfiguration.loadConfiguration(messagesESFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(this.getResource("messages_es.yml"),"UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messagesES.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public void saveMessagesES(){
        try{
            messagesES.save(messagesESFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void registerMessagesES(){
        messagesESFile = new File(this.getDataFolder(), "messages_es.yml");
        if(!messagesESFile.exists()){
            this.getMessagesES().options().copyDefaults(true);
            saveMessagesES();
        }
    }
}
