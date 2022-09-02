package sk.adr3ez.darkauth.bungee.events.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import sk.adr3ez.darkauth.bungee.BungeeMain;

import java.sql.SQLException;

public class PlayerProxyJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PostLoginEvent e) {
        if (BungeeMain.sql.isConnected()){

            /* Checks if session for player is created and if
            * the player is connecting from same IP adress
            * */
            if (BungeeMain.sqlGetter.sessions().exists(e.getPlayer().getName())) {
                if (BungeeMain.sqlGetter.sessions().compareIps(e.getPlayer().getName(),
                        e.getPlayer().getPendingConnection().getVirtualHost().getAddress().toString())) {

                    ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                            "&6Player session has been approved, sending player to lobby.")));
                    ServerInfo target = ProxyServer.getInstance().getServerInfo("lobby");
                    e.getPlayer().connect(target);
                }
            }
        } else {
            try {
                BungeeMain.sql.connect();
            } catch (SQLException ex) {
                e.getPlayer().disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        "&6Omlouváme se ale není možné načítat data z naší databáze\n\n&7Kontaktuj prosím naší podporu na discordu")));
            }
            if (BungeeMain.sql.isConnected()) {
                e.getPlayer().disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                        "&6Prosím skuste se připojit znovu zachvíli, pokud problém přetrváva kontaktuj prosím naší podporu na discordu")));
            }
        }
    }
}
