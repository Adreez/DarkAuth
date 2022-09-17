package sk.adr3ez.darkauth.bukkit.commands.commands;

import org.bukkit.entity.Player;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bukkit.commands.CommandInfo;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;

import java.util.Objects;

@CommandInfo(name = "authadmin", requiresPlayer = true, permission = "authadmin.*")
public class AuthAdmin extends PluginCommand {

    @Override
    public void execute(Player p, String[] args) {
        if (args[0].equalsIgnoreCase("setspawn")) {
            BukkitMain.config.get().set("Spawn.world", Objects.requireNonNull(p.getLocation().getWorld()).getName());
            BukkitMain.config.get().set("Spawn.x", p.getLocation().getX());
            BukkitMain.config.get().set("Spawn.y", p.getLocation().getY());
            BukkitMain.config.get().set("Spawn.z", p.getLocation().getZ());
            BukkitMain.config.get().set("Spawn.yaw", p.getLocation().getYaw());
            BukkitMain.config.get().set("Spawn.pitch", p.getLocation().getPitch());
            p.sendMessage("spawn has been set!");
        } /*else if (args[0].equalsIgnoreCase("sethologram")) {
            BukkitMain.config.get().set("Hologram.world", Objects.requireNonNull(p.getLocation().getWorld()).getName());
            BukkitMain.config.get().set("Hologram.x", p.getLocation().getX());
            BukkitMain.config.get().set("Hologram.y", p.getLocation().getY());
            BukkitMain.config.get().set("Hologram.z", p.getLocation().getZ());
        }*/
    }
}
