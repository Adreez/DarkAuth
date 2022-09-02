package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (!BukkitMain.config.get().getBoolean("Bungee")) {
            if (BukkitMain.sqlGetter.sessions().exists(String.valueOf(e.getPlayer()))) {
                BukkitMain.sqlGetter.sessions().deleteSession(e.getPlayer());
            }
        }
    }

}
