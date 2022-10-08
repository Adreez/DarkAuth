package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class MessageSend implements Listener {

    @EventHandler
    public void onCommandSent(AsyncPlayerChatEvent e) {
            if (!BukkitMain.sessionsManager.exists(e.getPlayer())) {
                if (!BukkitMain.config.get().getStringList("Settings.AllowedCommands").contains(e.getMessage())) {
                    e.getPlayer().sendMessage("Pro použití příkazu musíš být nejdříve přihlášen.");
                }
                e.getPlayer().sendMessage("Pro zaslání zprávy musíš být nejdříve přihlášen.");
                e.setCancelled(true);
            }
    }
}
