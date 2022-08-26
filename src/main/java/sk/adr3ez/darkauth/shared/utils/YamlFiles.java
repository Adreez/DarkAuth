package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sk.adr3ez.darkauth.bukkit.BukkitMain;
import sk.adr3ez.darkauth.bungee.BungeeMain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.logging.Level;

public class YamlFiles {

    private BukkitMain plugin = null;
    private BungeeMain bPlugin = null;
    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private final String fileName;

    public YamlFiles(BukkitMain plugin, String filename) {
        this.plugin = plugin;
        fileName = filename;
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        //Saves/initialises the config
        saveDefaultConfig();
    }

    public YamlFiles(BungeeMain plugin, String filename) {
        this.bPlugin = plugin;
        fileName = filename;
        if (!bPlugin.getDataFolder().exists())
            bPlugin.getDataFolder().mkdir();

        //Saves/initialises the config
        saveDefaultConfig();
    }

    public void reloadFiles() {
        if(plugin != null) {
            if(this.configFile == null)
                this.configFile = new File(this.plugin.getDataFolder(), fileName);
            this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

            InputStream defaultStream = this.plugin.getResource(fileName);
            if(defaultStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                this.dataConfig.setDefaults(defaultConfig);
            }
        } else {
            if(this.configFile == null)
                this.configFile = new File(this.bPlugin.getDataFolder(), fileName);
            this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

            InputStream defaultStream = this.bPlugin.getResourceAsStream(fileName);
            if(defaultStream != null) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
                this.dataConfig.setDefaults(defaultConfig);
            }
        }
    }

    public FileConfiguration get() {

        if(this.dataConfig == null)
            reloadFiles();
        return this.dataConfig;
    }

    public void saveConfig() {
        if (plugin != null) {
            //this.plugin.getLogger().log(Level.INFO, "File " + fileName + " has been saved!");
            if(this.dataConfig == null || this.configFile == null) {
                return;
            }

            try {
                this.get().save(this.configFile);
            } catch (IOException e) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
            }
        } else {
            //this.bPlugin.getLogger().log(Level.INFO, "File " + fileName + " has been saved!");
            if(this.dataConfig == null || this.configFile == null) {
                return;
            }

            try {
                this.get().save(this.configFile);
            } catch (IOException e) {
                this.bPlugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
            }
        }
    }

    public void saveDefaultConfig() {
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), fileName);
        if(!this.configFile.exists()) {
            if (plugin != null) {
                this.plugin.saveResource(fileName, false);
            } else {
                try (InputStream in = bPlugin.getResourceAsStream(fileName)) {
                    File file = new File(bPlugin.getDataFolder(), fileName);
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
