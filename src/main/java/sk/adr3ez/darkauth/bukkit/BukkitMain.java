package sk.adr3ez.darkauth.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import sk.adr3ez.darkauth.bukkit.commands.PluginCommand;
import sk.adr3ez.darkauth.bukkit.events.ListenerManager;
import sk.adr3ez.darkauth.shared.utils.SessionsManager;
import sk.adr3ez.darkauth.shared.utils.YamlFiles;
import sk.adr3ez.darkauth.shared.sql.MySQL;
import sk.adr3ez.darkauth.shared.sql.SQLGetter;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;


public final class BukkitMain extends JavaPlugin {

    public static YamlFiles config;
    public static MySQL mysql;
    public static SQLGetter sqlGetter;
    public static SessionsManager sessionsManager;
    @Override
    public void onEnable() {
        // Plugin startup logic

        config = new YamlFiles(this, "config.yml");
        mysql = new MySQL();
        sqlGetter = new SQLGetter();
        sessionsManager = new SessionsManager();
        new ListenerManager(this);

        /*
        * This will start looking for all commands in sk.adr3ez.darkauth.commands.commands
        * */
        for (Class<? extends PluginCommand> clazz : new Reflections(getClass().getPackage().getName() + ".commands.commands")
                .getSubTypesOf(PluginCommand.class)) {
            try {
                PluginCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                Objects.requireNonNull(getCommand(pluginCommand.getCommandInfo().name())).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (mysql.isConnected()) mysql.disconnect();
    }
}
