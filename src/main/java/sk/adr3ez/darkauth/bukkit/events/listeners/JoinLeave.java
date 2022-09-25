package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class JoinLeave implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        BukkitMain.sessionsManager.removeSession(e.getPlayer());
        e.getPlayer().teleport(new Location(Bukkit.getWorld(BukkitMain.config.get().getString("Spawn.world")),
                BukkitMain.config.get().getDouble("Spawn.x"),
                BukkitMain.config.get().getDouble("Spawn.y"),
                BukkitMain.config.get().getDouble("Spawn.z"),
                (float) BukkitMain.config.get().getDouble("Spawn.yaw"),
                (float) BukkitMain.config.get().getDouble("Spawn.pitch")));
        if (BukkitMain.config.get().getBoolean("Bungee")) {
            if (!BukkitMain.mysql.isConnected()) {
                e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&',
                        "&6Omlouváme se ale není možné načítat data z naší databáze\n\n&7Kontaktuj prosím naší podporu na discordu"));
            } else {
                if (!BukkitMain.sqlGetter.data().exists(e.getPlayer().getName())) {
                    e.getPlayer().sendMessage("§6§lZaregistruj sa pomocou: §7/register <heslo> <heslo>");
                } else {
                    e.getPlayer().sendMessage("§6§Prihlás sa pomocou: §7/login <heslo>");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (!BukkitMain.config.get().getBoolean("Settings.Bungee.enabled")) {
            BukkitMain.sessionsManager.removeSession(e.getPlayer());
        }
    }

}
