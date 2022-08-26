package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

public class RegisterService {

    protected Player player;
    protected String notHashedPass;

    public RegisterService(Player p) {
        player = p;
    }

    public RegisterService setPassword(String notHashedPassword) {
        notHashedPass = notHashedPassword;
        return this;
    }

    public boolean isRegistered() {
        return BukkitMain.sqlGetter.data().exists(player.getName());
    }

    /*
     * Get password from database
     */
    public String getHashedPassword() {
        return BukkitMain.sqlGetter.data().getHashedPassword(player.getName());
    }

    public boolean register() {
        if (player != null && notHashedPass != null) {
            if (BukkitMain.mysql.isConnected()) {
                String hashedPassword = new HashService(notHashedPass).hashPassword().getGeneratedPassword();

                BukkitMain.sqlGetter.data().createPlayer(player, hashedPassword);
                return true;
            }
        }
        return false;
    }

    /*
    Returns if passwords are same :D
     */
    public boolean login(String nothashedpass) {
        return new HashService(nothashedpass).hashPassword().getGeneratedPassword().equals(BukkitMain.sqlGetter.data().getHashedPassword(player.getName()));
    }

}
