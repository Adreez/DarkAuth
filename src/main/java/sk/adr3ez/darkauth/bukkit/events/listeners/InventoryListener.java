package sk.adr3ez.darkauth.bukkit.events.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

public class InventoryListener implements Listener {

    /*@EventHandler
    public void onInventoryLoad(InventoryClickEvent e) {
        if (Objects.requireNonNull(e.getClickedInventory()).getType().equals(InventoryType.ANVIL)) {
            if (e.getView().getTitle().equals("§0Prihlasenie")) {
                if (e.getClickedInventory().getItem(3) != null) {
                    e.getWhoClicked().sendMessage(e.getClickedInventory().getItem(3).getItemMeta().getDisplayName());
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
                }
                e.setCancelled(true);
            }
        }
    }*/
}
