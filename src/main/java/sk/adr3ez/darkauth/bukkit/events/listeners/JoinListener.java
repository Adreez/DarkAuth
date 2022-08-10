package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        if (!BukkitMain.mysql.isConnected()) {
            e.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&',
                    "&6Omlouváme se ale není možné načítat data z naší databáze\n\n&7Kontaktuj prosím naší podporu na discordu"));
        }
    }

}
