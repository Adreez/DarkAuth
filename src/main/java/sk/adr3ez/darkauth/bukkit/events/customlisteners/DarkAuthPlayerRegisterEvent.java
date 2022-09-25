package sk.adr3ez.darkauth.bukkit.events.customlisteners;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DarkAuthPlayerRegisterEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private boolean isCancelled;
    private final boolean isSuccessful;

    public DarkAuthPlayerRegisterEvent(Player player, boolean successful) {
        this.player = player;
        isCancelled = false;
        isSuccessful = successful;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}
