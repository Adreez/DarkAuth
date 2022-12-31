package sk.adr3ez.darkauth.bukkit.commands.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bukkit.commands.CommandInfo;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;
import sk.adr3ez.darkauth.bukkit.events.customlisteners.DarkAuthPlayerLoginEvent;
import sk.adr3ez.darkauth.shared.utils.HashService;

//@Command(name = "login", aliases = {"l", "log"}, usage = "/<command> <password>")
@CommandInfo(name = "login", requiresPlayer = true)
public class Login extends PluginCommand {


    @Override
    public void execute(Player p, String[] args) {
       /* if (BukkitMain.sqlGetter.data().exists(p.getName())) {
            if (args.length == 1) {

                String password = new HashService(args[0]).get();

                if (password.equals(BukkitMain.sqlGetter.data().getHashedPassword(p.getName()))) {
                    Bukkit.getPluginManager().callEvent(new DarkAuthPlayerLoginEvent(p));
                } else {
                    p.sendMessage("Zadal si nesprávne heslo!");
                }
            } else {
                p.sendMessage("Použij /login <heslo>");
            }
        } else {
            p.sendMessage("You must use /register <pass> <pass>");
        }*/
    }

}
