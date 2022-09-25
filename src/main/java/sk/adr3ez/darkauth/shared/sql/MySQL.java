package sk.adr3ez.darkauth.shared.sql;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL {

    public MySQL() {
        try {
            connect();
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.WARNING, "Unable to load database");
            //throw new RuntimeException(e);
        }
    }

    private static Connection connection;

    public boolean isConnected() {
        return (connection != null);
    }

    public void connect() throws SQLException {
        if (!isConnected()) {
            String password = "";
            boolean useSSL = false;
            String database = "darkauth";
            String username = "root";
            String port = "3306";
            String host = "localhost";
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL, username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
