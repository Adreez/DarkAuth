package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!BukkitMain.sqlGetter.sessions().exists(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }
}
