package sk.adr3ez.darkauth.bungee.events.listeners;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerProxyLeave implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onProxyLeave(final PlayerDisconnectEvent e) {
        // SEND SESSION ACTION TO SPIGOT!!!
    }

}
