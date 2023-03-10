package sk.adr3ez.darkauth.bungee.events;

import net.md_5.bungee.api.plugin.Listener;
import org.reflections.Reflections;
import sk.adr3ez.darkauth.bungee.BungeeMain;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public class ListenerManager {

    private final BungeeMain plugin;

    public ListenerManager(BungeeMain plugin) {
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
                plugin.getProxy().getPluginManager().registerListener(plugin, listener);
                plugin.getLogger().log(Level.INFO, "Listener " + clazz.getName() + " has been loaded!");
            } catch (RuntimeException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
