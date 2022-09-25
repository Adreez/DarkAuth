package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class MessageSend implements Listener {

    @EventHandler
    public void onMessageSent(AsyncPlayerChatEvent e) {
        if (!BukkitMain.sqlGetter.sessions().exists(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommandSent(ServerCommandEvent e) {
        if (e.getSender() instanceof Player) {
            if (!BukkitMain.sqlGetter.sessions().exists(e.getSender().getName())) {
                if (!BukkitMain.config.get().getStringList("Settings.AllowedCommands").contains(e.getCommand())) {
                    e.setCancelled(true);
                    e.getSender().sendMessage("Pro použití příkazu musíš být nejdříve přihlášen.");
                }
            }
        }
    }

}
