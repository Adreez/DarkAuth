package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;


/*
Session is created when player will successfuly log in.
*/
public class Session {

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getLoginTimeMillis() {
        return loginTimeMillis;
    }

    public InetSocketAddress getLoginIp() {
        return loginIp;
    }
    private final Player player;
    private final UUID uuid;
    private final long loginTimeMillis;
    private final InetSocketAddress loginIp;

    public Session(Player p, long loginTimeMillis, InetSocketAddress loginIp) {
        player = p;
        uuid = p.getUniqueId();
        this.loginTimeMillis = loginTimeMillis;
        this.loginIp = loginIp;

    }

}
