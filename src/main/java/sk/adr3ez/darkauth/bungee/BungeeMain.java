package sk.adr3ez.darkauth.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import sk.adr3ez.darkauth.shared.utils.YamlFiles;
import sk.adr3ez.darkauth.bungee.events.ListenerManager;

public class BungeeMain extends Plugin {

    public static YamlFiles config;

    @Override
    public void onEnable() {
        new ListenerManager(this);

        config = new YamlFiles(this, "config.yml");
    }

}
