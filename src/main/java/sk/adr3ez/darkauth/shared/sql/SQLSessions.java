package sk.adr3ez.darkauth.shared.sql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLSessions {

    String table = "sessions";


    public void createTable() {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(nick VARCHAR(100), " +
                    "uuid VARCHAR(100), loginMillis BIGINT(100), ip VARCHAR(100), PRIMARY KEY (nick))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Creates new session only on sucessfull login
     */
    public void createSession(Player player) {
        try {
            if (!exists(player.getName())) {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO " + table + "(nick,uuid,loginMillis,ip) VALUES (?,?,?,?)");
                ps.setString(1, player.getName());
                ps.setString(2, player.getUniqueId().toString());
                ps.setLong(3, new Date().getTime());
                ps.setString(4, String.valueOf(player.getAddress()));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSession(Player player) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE * FROM " + table + " WHERE nick=?");
            ps.setLong(1, new Date().getTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String player) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE nick=?");
            ps.setString(1, player);
            ResultSet results = ps.executeQuery();
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean compareIps(String nick, String ip1) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT ip FROM " + table + " WHERE nick=?");
            ps.setString(1, nick);
            ResultSet rs = ps.executeQuery();
            String ip2 = rs.getString("ip");
            return ip1.equals(ip2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
