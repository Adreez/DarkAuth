package sk.adr3ez.darkauth.bukkit.commands.commands;

import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.commands.CommandInfo;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;
import sk.adr3ez.darkauth.shared.utils.RegisterService;

@CommandInfo(name = "register", requiresPlayer = true)
public class Register extends PluginCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (args.length == 2) {
            if (args[1].equals(args[0])) {
                RegisterService rs = new RegisterService(p);

                if (!rs.isRegistered()) {
                    if (rs.setPassword(args[1]).register()) {
                        p.sendMessage("Sucessfully registered! - Use /login <pass> to login to server");
                    } else {
                        p.sendMessage("Něco se pokazilo, kontaktuj prosím naši podporu nebo to skús znovu :D");
                    }
                } else {
                    p.sendMessage("už si zaregistrovaný! Použij /login <heslo>");
                }
            } else {
                p.sendMessage("Hesla sa nezhodujú!");
            }
        } /*else {
            p.sendMessage("Príkaz musí byť zadaný takto: /register <heslo> <znovuHeslo>");
        }*/
    }
}
