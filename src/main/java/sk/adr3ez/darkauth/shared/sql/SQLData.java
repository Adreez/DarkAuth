package sk.adr3ez.darkauth.shared.sql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class SQLData {

    String table = "data";


    public void createTable() {
        PreparedStatement ps;
        try {
            ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS " + table + "(nick VARCHAR(100), " +
                    "uuid VARCHAR(100), password VARCHAR(255), lastLoginMillis BIGINT(100), ip VARCHAR(100),2fa BOOLEAN, PRIMARY KEY (nick))");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player, String hashedPassword) {
        try {
            UUID uuid = player.getUniqueId();
            if (!exists(player.getName())) {
                PreparedStatement ps2 = MySQL.getConnection().prepareStatement("INSERT INTO " + table + "(nick,uuid,lastLoginMillis,ip,password) VALUES (?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setString(2, uuid.toString());
                ps2.setLong(3, new Date().getTime());
                ps2.setString(4, String.valueOf(player.getAddress()));
                ps2.setString(5, hashedPassword);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String nick) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE nick=?");
            ps.setString(1, nick);
            ResultSet results = ps.executeQuery();
            return results.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setTimeMillis(Player p) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET lastLoginMillis=? WHERE nick=?");
            ps.setLong(1, new Date().getTime());
            ps.setString(2, p.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setIpAdress(Player p) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET ip=? WHERE nick=?");
            ps.setString(1, String.valueOf(p.getAddress()));
            ps.setString(2, p.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getHashedPassword(String nick) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT password FROM " + table + " WHERE nick=?");
            ps.setString(1, nick);
            ResultSet rs = ps.executeQuery();
            String pass;
            if (rs.next()) {
                pass = rs.getString("password");
                return pass;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
