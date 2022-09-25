package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bukkit.events.customlisteners.DarkAuthPlayerRegisterEvent;

public class RegisterService {

    protected final Player player;
    protected final String hashedPass;

    public RegisterService(Player p, String hashedPass) {
        player = p;
        this.hashedPass = hashedPass;
    }

    public boolean isRegistered() {
        return BukkitMain.sqlGetter.data().exists(player.getName());
    }
    public boolean register() {
        if (player != null && hashedPass != null) {
            if (BukkitMain.mysql.isConnected()) {
                BukkitMain.sqlGetter.data().createPlayer(player, hashedPass);
                Bukkit.getPluginManager().callEvent(new DarkAuthPlayerRegisterEvent(player, true));
                return true;
            } else {
                Bukkit.getPluginManager().callEvent(new DarkAuthPlayerRegisterEvent(player, false));
            }
        }
        return false;
    }
}
