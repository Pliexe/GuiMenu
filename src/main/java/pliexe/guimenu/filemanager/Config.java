package pliexe.guimenu.filemanager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;

public class Config {
    FileConfiguration config;

    public Config(InputStreamReader inputStreamReader) {
        this.config = YamlConfiguration.loadConfiguration(inputStreamReader);
    }
}
