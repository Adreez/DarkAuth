package sk.adr3ez.darkauth.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import sk.adr3ez.darkauth.bungee.events.ListenerManager;
import sk.adr3ez.darkauth.shared.sql.MySQL;
import sk.adr3ez.darkauth.shared.sql.SQLGetter;

import java.sql.SQLException;

public class BungeeMain extends Plugin {

    //public static YamlFiles config;
    public static MySQL sql;
    public static SQLGetter sqlGetter;

    @Override
    public void onEnable() {

        sql = new MySQL();
        sqlGetter = new SQLGetter();

        new ListenerManager(this);
        //config = new YamlFiles(this, "config.yml");
    }

}
