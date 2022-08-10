package sk.adr3ez.darkauth.bukkit.events;

import org.bukkit.event.Listener;
import org.reflections.Reflections;
import sk.adr3ez.darkauth.bukkit.BukkitMain;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public class ListenerManager {

    private final BukkitMain plugin;

    public ListenerManager(BukkitMain plugin) {
        this.plugin = plugin;
        registerListeners();
    }

    public void registerListeners() throws RuntimeException {
        String packageName = getClass().getPackage().getName();

        for (Class<?> clazz : new Reflections(packageName + ".listeners")
                .getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor()
                        .newInstance();
                plugin.getServer().getPluginManager().registerEvents(listener, plugin);
                plugin.getLogger().log(Level.INFO, "Listener " + clazz.getName() + " has been loaded!");
            } catch (RuntimeException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
