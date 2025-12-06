package sanswee.mvtest;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class LastLocationSave {
    private final File file;
    private final FileConfiguration config;

    public LastLocationSave(File dataFolder){
        this.file = new File(dataFolder, "lastlocation.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save(Map<UUID, Map<String, Location>> data) {
        for (String key : config.getKeys(false)){
            config.set(key, null);
        }

    }
}
