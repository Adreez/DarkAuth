package sk.adr3ez.darkauth.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;
import sk.adr3ez.darkauth.bukkit.events.ListenerManager;
import sk.adr3ez.darkauth.shared.utils.YamlFiles;
import sk.adr3ez.darkauth.shared.sql.MySQL;
import sk.adr3ez.darkauth.shared.sql.SQLGetter;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;


public final class BukkitMain extends JavaPlugin {

    public static YamlFiles config;
    public static MySQL mysql;
    public static SQLGetter sqlGetter;
    @Override
    public void onEnable() {
        // Plugin startup logic
        new ListenerManager(this);

        config = new YamlFiles(this, "config.yml");
        mysql = new MySQL();
        sqlGetter = new SQLGetter();

        for (Class<? extends PluginCommand> clazz : new Reflections(getClass().getPackage().getName() + ".commands.commands")
                .getSubTypesOf(PluginCommand.class)) {
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                Objects.requireNonNull(getCommand(pluginCommand.getCommandInfo().name())).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if (mysql.isConnected()) {
            sqlGetter.data().createTable();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (mysql.isConnected()) {
            mysql.disconnect();
        }
    }
}
