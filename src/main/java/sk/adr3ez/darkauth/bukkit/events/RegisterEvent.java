package sk.adr3ez.darkauth.bukkit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class RegisterEvent extends Event {

    public RegisterEvent(boolean sucessfull, Player player, String hash, PlayerLoginEvent event) {
        this.sucessfull = sucessfull;
        this.player = player;
        this.hash = hash;
        this.event = event;
    }

    private final boolean sucessfull;
    private final Player player;
    private final String hash;
    private final PlayerLoginEvent event;

    public boolean isSucessfull() {
        return sucessfull;
    }

    public Player getPlayer() {
        return player;
    }

    public String getHash() {
        return hash;
    }

    public PlayerLoginEvent getEvent() {
        return event;
    }
    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
