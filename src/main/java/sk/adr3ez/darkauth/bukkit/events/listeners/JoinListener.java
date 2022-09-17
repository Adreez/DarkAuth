package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
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

}
