/*
 * Project: MySQL
 * Author:  Revax812
 * Version: 1.0
 * Last Change:
 *    by:   Revax812
 *    date: 23.06.2022, 15:19
 * Copyright (c): Revax812, 2022
 */

package de.revax812.mysql;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import de.revax812.customconfig.Config;

import java.sql.*;

/**
 * Allows to create and execute custom MySQL Databases for Minecraft Spigot.<br>
 * Bugs: none known
 * <br><br>
 * Copyright (c): Revax812, 2022<br>
 *
 * @author Revax812 <br>
 * @version 1.0
 */

public class MySQL {

    /**
     * The public {@link Connection} connection
     */
    public Connection connection;
    /**
     * The private {@link String} database
     */
    private String database;
    /**
     * The private {@link String} host
     */
    private String host;
    /**
     * The private {@link String} username
     */
    private String username;
    /**
     * The private {@link String} password
     */
    private String password;
    /**
     * The private {@link Integer} port
     */
    private int port;
    /**
     * The private {@link Boolean} autoReconnect
     */
    private boolean autoReconnect;
    /**
     * The private {@link Config} mysql
     */
    private Config mysql;
    /**
     * The private final {@link Config} languageSettings
     */
    private final Config languageSettings;

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin.
     * <br><br>
     * Redirects to {@link #MySQL(boolean, Plugin, String, String)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     * Default ConfigName is mysql.yml.<br>
     *
     * @param plugin represents the {@link Plugin} plugin like the Main class<br>
     * @see #MySQL(boolean, Plugin, String, String)
     */
    public MySQL(Plugin plugin) {
        this(false, plugin, "settings/mysql/", "mysql.yml");
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin and {@link Boolean} autoReconnect.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     * Default ConfigName is mysql.yml.<br>
     *
     * @param plugin        represents the {@link Plugin} plugin like the Main class<br>
     * @param autoReconnect represents the {@link Boolean} autoReconnect for MySQL<br>
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, boolean autoReconnect) {
        this(plugin, "settings/mysql/", "mysql.yml", autoReconnect, false);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin, {@link Boolean} autoReconnect
     * and {@link Boolean} copyDefaults.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     * Default ConfigName is mysql.yml.<br>
     *
     * @param plugin        represents the {@link Plugin} plugin like the Main class<br>
     * @param autoReconnect represents the {@link Boolean} autoReconnect for MySQL<br>
     * @param copyDefaults  represents the {@link Boolean} copyDefault for the {@link Config} customConfig
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, boolean autoReconnect, boolean copyDefaults) {
        this(plugin, "settings/mysql/", "mysql.yml", autoReconnect, copyDefaults);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin and {@link String} customConfigName.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, String customConfigName) {
        this(false, plugin, "settings/mysql/", customConfigName);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin, {@link String} customConfigName
     * and {@link Boolean} autoReconnect.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @param autoReconnect    represents the {@link Boolean} autoReconnect for MySQL<br>
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, String customConfigName, boolean autoReconnect) {
        this(plugin, "settings/mysql/", customConfigName, autoReconnect, false);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin, {@link String} customConfigName,
     * {@link Boolean} autoReconnect and {@link Boolean} copyDefaults.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     * Default ConfigPath is settings/mysql/.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @param autoReconnect    represents the {@link Boolean} autoReconnect for MySQL<br>
     * @param copyDefaults     represents the {@link Boolean} copyDefaults for the {@link Config} customConfig<br>
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, String customConfigName, boolean autoReconnect, boolean copyDefaults) {
        this(plugin, "settings/mysql/", customConfigName, autoReconnect, copyDefaults);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin,
     * {@link String} customConfigPath and {@link String} customConfigName.
     * <br><br>
     * Redirects to {@link #MySQL(boolean, Plugin, String, String)}.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigPath represents the {@link String} path of the {@link Config} config<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @see #MySQL(boolean, Plugin, String, String)
     */
    public MySQL(Plugin plugin, String customConfigPath, String customConfigName) {
        this(false, plugin, customConfigPath, customConfigName);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin,
     * {@link String} customConfigPath, {@link String} customConfigName and {@link Boolean} autoReconnect.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, String, boolean, boolean)}.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigPath represents the {@link String} path of the {@link Config} config<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @param autoReconnect    represents the {@link Boolean} autoReconnect for MySQL<br>
     * @see #MySQL(Plugin, String, String, boolean, boolean)
     */
    public MySQL(Plugin plugin, String customConfigPath, String customConfigName, boolean autoReconnect) {
        this(plugin, customConfigPath, customConfigName, autoReconnect, false);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin,
     * {@link String} customConfigPath, {@link String} customConfigName,
     * {@link Boolean} autoReconnect and {@link Boolean} copyDefaults.
     * <br><br>
     * First, a new {@link Config} mysql and a new {@link Config} languageSettings is created.<br>
     * After that, the values of the {@link Config} mysql are set and the method {@link #connect()} is executed.<br>
     *
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigPath represents the {@link String} path of the {@link Config} config<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @param autoReconnect    represents the {@link Boolean} autoReconnect for MySQL<br>
     * @param copyDefaults     represents the {@link Boolean} copyDefaults for the {@link Config} customConfig<br>
     * @see #connect()
     */
    public MySQL(Plugin plugin, String customConfigPath, String customConfigName, boolean autoReconnect, boolean copyDefaults) {
        this.mysql = new Config(plugin, customConfigPath, customConfigName, copyDefaults);
        this.languageSettings = new Config(plugin, "settings/mysql/", "languageSettings.yml", true);

        if (!mysql.isSet("MySQL.")) setupConfig(plugin);
        else {
            this.database = mysql.getString("MySQL." + ".database");
            this.port = mysql.getInt("MySQL." + ".port");
            this.host = mysql.getString("MySQL." + ".host");
            this.username = mysql.getString("MySQL." + ".username");
            this.password = mysql.getString("MySQL." + ".password");
            this.autoReconnect = autoReconnect;
        }

        connect();
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Boolean} copyDefaults and {@link Plugin} plugin.
     * <br><br>
     * Redirects to {@link #MySQL(boolean, Plugin, String, String)}.<br>
     *
     * @param copyDefaults represents the {@link Boolean} copyDefaults for the {@link Config} customConfig
     * @param plugin       represents the {@link Plugin} plugin like the Main class<br>
     * @see #MySQL(boolean, Plugin, String, String)
     */
    public MySQL(boolean copyDefaults, Plugin plugin) {
        this(copyDefaults, plugin, "settings/mysql/", "mysql.yml");
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Boolean} copyDefaults,
     * {@link Plugin} plugin and {@link String} customConfigName.
     * <br><br>
     * Redirects to {@link #MySQL(boolean, Plugin, String, String)}.<br>
     *
     * @param copyDefaults represents the {@link Boolean} copyDefaults for the {@link Config} customConfig
     * @param plugin       represents the {@link Plugin} plugin like the Main class<br>
     * @see #MySQL(boolean, Plugin, String, String)
     */
    public MySQL(boolean copyDefaults, Plugin plugin, String customConfigName) {
        this(copyDefaults, plugin, "settings/mysql/", customConfigName);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Boolean} copyDefaults,
     * {@link Plugin} plugin, {@link String} customConfigPath and {@link String} customConfigName.
     * <br><br>
     * First, a new {@link Config} mysql and a new {@link Config} languageSettings is created.<br>
     * After that, the values of the {@link Config} mysql are set and the method {@link #connect()} is executed.<br>
     *
     * @param copyDefaults     represents the {@link Boolean} copyDefaults for the {@link Config} customConfig<br>
     * @param plugin           represents the {@link Plugin} plugin like the Main class<br>
     * @param customConfigPath represents the {@link String} path of the {@link Config} config<br>
     * @param customConfigName represents the {@link String} name of the {@link Config} config<br>
     * @see #connect()
     */
    public MySQL(boolean copyDefaults, Plugin plugin, String customConfigPath, String customConfigName) {
        this.mysql = new Config(plugin, customConfigPath, customConfigName, copyDefaults);
        this.languageSettings = new Config(plugin, "settings/mysql/", "languageSettings.yml", true);

        if (!mysql.isSet("MySQL.")) setupConfig(plugin);
        else {
            this.database = mysql.getString("MySQL." + ".database");
            this.port = mysql.getInt("MySQL." + ".port");
            this.host = mysql.getString("MySQL." + ".host");
            this.username = mysql.getString("MySQL." + ".username");
            this.password = mysql.getString("MySQL." + ".password");
            this.autoReconnect = mysql.getBoolean("MySQL." + "autoReconnect");
        }

        connect();
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin, {@link String} database,
     * {@link Integer} port, {@link String} host, {@link String} username and {@link String} password.
     * <br><br>
     * Redirects to {@link #MySQL(Plugin, String, int, String, String, String, boolean)}.<br>
     *
     * @param plugin   represents the {@link Plugin} plugin like the Main class<br>
     * @param database represents the {@link String} database name<br>
     * @param port     represents the {@link Integer} port of the database<br>
     * @param host     represents the {@link String} host of the database<br>
     * @param username represents the {@link String} username of the account for the database<br>
     * @param password represents the {@link String} password of the account for the database<br>
     * @see #MySQL(Plugin, String, int, String, String, String, boolean)
     */
    public MySQL(Plugin plugin, String database, int port, String host, String username, String password) {
        this(plugin, database, port, host, username, password, false);
    }

    /**
     * Creates a new {@link MySQL} MySQL Database Connection with {@link Plugin} plugin, {@link String} database,
     * {@link Integer} port, {@link String} host, {@link String} username,
     * {@link String} password and {@link Boolean} autoReconnect.
     * <br><br>
     * The values of the MySQL Database Connection are set and the method {@link #connect()} is executed.<br>
     *
     * @param plugin        represents the {@link Plugin} plugin like the Main class<br>
     * @param database      represents the {@link String} database name<br>
     * @param port          represents the {@link Integer} port of the database<br>
     * @param host          represents the {@link String} host of the database<br>
     * @param username      represents the {@link String} username of the account for the database<br>
     * @param password      represents the {@link String} password of the account for the database<br>
     * @param autoReconnect represents the {@link Boolean} autoReconnect for MySQL<br>
     * @see #connect()
     */
    public MySQL(Plugin plugin, String database, int port, String host, String username, String password, boolean autoReconnect) {
        this.languageSettings = new Config(plugin, "settings/mysql/", "languageSettings.yml", true);
        this.database = database;
        this.port = port;
        this.host = host;
        this.username = username;
        this.password = password;
        this.autoReconnect = autoReconnect;

        connect();
    }

    /**
     * Private method for setting up the {@link Config} mysql.
     * <br><br>
     *
     * @param plugin represents the {@link Plugin} plugin like the Main class
     */
    private void setupConfig(Plugin plugin) {
        mysql.set("MySQL." + ".database", languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupDatabaseName"));
        mysql.set("MySQL." + ".port", languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupDatabasePort"));
        mysql.set("MySQL." + ".host", languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupDatabaseHost"));
        mysql.set("MySQL." + ".username", languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupUsername"));
        mysql.set("MySQL." + ".password", languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupPassword"));
        mysql.set("MySQL." + "autoReconnect", false);
        mysql.set("MySQL." + ".allowClearConfig", false);

        String[] message = languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigSetupConfigurationNotSetError").split("PACKAGELOCATION");
        Bukkit.getConsoleSender().sendMessage(message[0] + plugin.getDataFolder() + "\\settings\\mysql\\mysql.yml" + message[1]);
    }

    /**
     * Sets or updates a value of the {@link MySQL} database.
     * <br><br>
     *
     * @param qry represents the {@link MySQL} command
     */
    public void update(String qry) {
        try {
            PreparedStatement st = getConnection().prepareStatement(qry);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    /**
     * Gets a value out of the {@link MySQL} database.
     * <br><br>
     *
     * @param qry  represents the {@link MySQL} command<br>
     * @return     the {@link ResultSet} rs
     */
    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            PreparedStatement st = getConnection().prepareStatement(qry);
            rs = st.executeQuery();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    /**
     * Connects the MySQL Database.
     * <br><br>
     * Redirects to {@link #connect(boolean)}.
     */
    public void connect() {
        connect(false);
    }

    /**
     * Connects the MySQL Database.
     * <br><br>
     *
     * @param useSSL represents the {@link Boolean} useSSL for the {@link Connection} connection
     */
    public void connect(boolean useSSL) {
        if (!hasConnection()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password);
                if (isConnected())
                    Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".MySQLConnected"));
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".MySQLConnectionError"));
            }
        }

    }

    /**
     * Disconnects the MySQL Database.
     * <br><br>
     * Redirects to {@link #disconnect(boolean)}.
     */
    public void disconnect() {
        disconnect(false);
    }

    /**
     * Disconnects the MySQL Database.
     * <br><br>
     *
     * @param ignoreAutoReconnect represents the {@link Boolean} ignoreAutoReconnect for the {@link Connection} connection
     */
    public void disconnect(boolean ignoreAutoReconnect) {
        if (hasConnection()) {
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".MySQLDisconnected"));
                if (!ignoreAutoReconnect && autoReconnect) connect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reconnects the MySQL Database.
     * <br><br>
     */
    public void reconnect() {
        disconnect();
        connect();
        Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".MySQLReconnected"));
    }

    /**
     * Sets AutoReconnect for the MySQL Database.
     * <br><br>
     *
     * @param enable represents the {@link Boolean} enable for {@link Boolean} autoReconnect
     */
    public void autoReconnect(boolean enable) {
        this.autoReconnect = enable;
    }

    /**
     * Clears the MySQL-Config-Settings.
     * <br><br
     */
    public void clearMySQLConfig() {
        if (mysql != null) {
            if (getAllowClear()) {
                mysql.clear();
                Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigDeleted"));
            } else
                Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigDeletionErrorAllowClearConfigFalse"));
        } else
            Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigMySQlNotSavedInError"));
    }

    /**
     * Clears a Path of the MySQL-Config-Settings.
     * <br><br>
     *
     * @param paths represents the {@link MySQLConfigPaths} paths that should be deleted
     */
    public void clearMySQLConfigPath(MySQLConfigPaths paths) {
        if (mysql != null) {
            if (getAllowClear()) {
                mysql.clearPath(paths.getPath());
                String[] message = languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigPathDeleted").split("CONFIGPATH");
                Bukkit.getConsoleSender().sendMessage(message[0] + "\"" + paths.name() + "\"" + message[1]);
            } else
                Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigPathDeletionErrorAllowClearConfigFalse"));
        } else
            Bukkit.getConsoleSender().sendMessage(languageSettings.getString("Messages." + ".ConfigMessages." + ".ConfigMySQlNotSavedInError"));
    }

    /**
     * Checks if the MySQL Database has a {@link Connection} connection.
     * <br><br>
     *
     * @return the {@link Boolean} hasConnection
     */
    public Boolean hasConnection() {
        return connection != null;
    }

    /**
     * Checks if the MySQL Database is connected.
     * <br><br>
     *
     * @return the {@link Boolean} isConnected
     */
    public Boolean isConnected() {
        return connection != null;
    }

    /**
     * Gets the {@link Connection} connection.
     * <br><br>
     *
     * @return the {@link Connection} connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Gets the {@link String} database.
     * <br><br>
     *
     * @return the {@link String} database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Gets the {@link Integer} port.
     * <br><br>
     *
     * @return the {@link Integer} port
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the {@link String} host.
     * <br><br>
     *
     * @return the {@link String} host
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the {@link String} username.
     * <br><br>
     *
     * @return the {@link String} username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the {@link String} password.
     * <br><br>
     *
     * @return the {@link String} password
     */
    public String getPassword() {
        return username;
    }

    /**
     * Gets the {@link Boolean} autoReconnect.
     * <br><br>
     *
     * @return the {@link Boolean} autoReconnect
     */
    public Boolean getAutoReconnect() {
        return autoReconnect;
    }

    /**
     * Gets the {@link Boolean} autoReconnect out of the {@link Config} config.
     * <br><br>
     *
     * @return the {@link Boolean} autoReconnect
     */
    public Boolean getAutoReconnectFromConfig() {
        return mysql.getBoolean("MySQL." + "autoReconnect");
    }

    /**
     * Gets the {@link Boolean} allowClear.
     * <br><br>
     *
     * @return the {@link Boolean} allowClear
     */
    public Boolean getAllowClear() {
        return mysql.getBoolean("MySQL." + ".allowClearConfig");
    }

    /**
     * MySQLConfigPaths for the {@link MySQL} MySQL Database.<br>
     * Bugs: none known
     * <br><br>
     * Copyright (c): Revax812, 2022<br>
     *
     * @author Revax812 <br>
     * @version 1.0
     * @see MySQL
     */

    public enum MySQLConfigPaths {

        /**
         * The DATABASE Config String
         */
        DATABASE("MySQL." + ".database"),
        /**
         * The PORT Config String
         */
        PORT("MySQL." + ".port"),
        /**
         * The HOST Config String
         */
        HOST("MySQL." + ".host"),
        /**
         * The USERNAME Config String
         */
        USERNAME("MySQL." + ".username"),
        /**
         * The PASSWORD Config String
         */
        PASSWORD("MySQL." + ".password"),
        /**
         * The AUTORECONNECT Config String
         */
        AUTORECONNECT("MySQL." + "autoReconnect"),
        /**
         * The ALLOW_CLEAR Config String
         */
        ALLOW_CLEAR("MySQL." + ".allowClearConfig");

        /**
         * The private final {@link String} path
         */
        private final String path;

        /**
         * Inits the MySQLConfigPaths of the MySQL Config.
         * <br><br>
         *
         * @param path represents the {@link String} path of the config
         */
        MySQLConfigPaths(String path) {
            this.path = path;
        }

        /**
         * Gets the {@link String} MySQL Config path.
         * <br><br>
         *
         * @return the {@link String} path
         */
        public String getPath() {
            return this.path;
        }

    }

}