package sk.adr3ez.darkauth.bukkit.commands.commands;

import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bukkit.commands.CommandInfo;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;
import sk.adr3ez.darkauth.shared.utils.RegisterService;

@CommandInfo(name = "login", requiresPlayer = true)
public class Login extends PluginCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (BukkitMain.sqlGetter.data().exists(p.getName())) {
            if (args.length == 1) {
                String password = args[0];

                RegisterService rs = new RegisterService(p);

                if (rs.getHashedPassword() != null) {
                    if (rs.login(password)) {
                        BukkitMain.sqlGetter.sessions().createSession(p, false);
                        p.sendMessage("You logged in!");
                    } else {
                        p.sendMessage("Zadal si nesprávne heslo!");
                    }
                }
            } else {
                p.sendMessage("Použij /login <heslo>");
            }
        } else {
            p.sendMessage("You must use /register <pass> <pass>");
        }
    }

}
