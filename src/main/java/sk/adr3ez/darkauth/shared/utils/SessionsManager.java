package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

import java.util.HashMap;

public class SessionsManager {

    private final HashMap<Player,Session> sessions = new HashMap<>();

    public void addSession(Session session) {
        sessions.put(session.getPlayer(),session);
        BukkitMain.sqlGetter.sessions().createSession(session.getPlayer(), false);
    }

    public void removeSession(Player p){
        sessions.remove(p);
        if (BukkitMain.sqlGetter.sessions().exists(p.getName())) {
            BukkitMain.sqlGetter.sessions().deleteSession(p);
        }
    }

}
