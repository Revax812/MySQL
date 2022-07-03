 ![Header](./header.png)
# MySQL
Simple Custom MySQL Databases for Minecraft Spigot

The following should be roughly in the main class:

```java
//Creating two separate custom MySQL Database Connections
private final MySQL configSql = new MySQL(this);
private final MySQL hardcodeSql = new MySQL(this, "database", 3306, "host", "username", "password");
```

The first MySQL object creates a new MySQL Database Connection. The values must be entered in the created config (located in "YourPlugin/settings/mysql/mysql.yml").
The second MySQL object creates a new MySQL Database Connection with the entered values.

A file called languageSettings.yml is automaticly created and located in "YourPlugin/settings/mysql/languageSettings.yml".
Here, you can change all output messages of the MySQL Database Connection.

After that, you should create these methods:

```java
//Creating methods for the custom configs
public MySQL getMySQL() {
        return configSql
    }

public MySQL getHardcodeSQL() {
        return hardcodeSql
    }
```

Now you can call these custom MySQL Database Connections via the main class.
