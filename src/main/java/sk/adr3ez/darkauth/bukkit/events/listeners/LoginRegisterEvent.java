package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bukkit.events.customlisteners.DarkAuthPlayerLoginEvent;
import sk.adr3ez.darkauth.bukkit.events.customlisteners.DarkAuthPlayerRegisterEvent;
import sk.adr3ez.darkauth.shared.utils.Session;

import java.util.Date;

public class LoginRegisterEvent implements Listener {

    @EventHandler
    public void onPlayerLogin(DarkAuthPlayerLoginEvent e) {
        if (BukkitMain.mysql.isConnected()) {
            BukkitMain.sessionsManager.addSession(new Session(e.getPlayer(), new Date().getTime(), e.getPlayer().getAddress()));
            e.getPlayer().sendMessage("You successfuly loged in!");
        } else {
            e.setCancelled(true);
            e.getPlayer().sendMessage("Chyba! Skús sa prihlásiť znovu, ak chyba pretrváva kontaktuj prosím podporu.");
        }
    }

    @EventHandler
    public void onPlayerRegisterEvent(DarkAuthPlayerRegisterEvent e) {
        Player p = e.getPlayer();
        if (e.isSuccessful()) {
            p.sendMessage("Successfuly registered!");
        } else {
            p.sendMessage("Failed to register!");
        }
    }
}
