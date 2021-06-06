package moe.windleaf.WLKits.Plugins.SimpleWarp;

import moe.windleaf.WLKits.Utils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WarpManager {
    public FileConfiguration warps;
    public File file;

    public void init() {
        warps = new YamlConfiguration();
        try {
            file = new File(SimpleWarp.path);
            if (!file.exists()) {
                @SuppressWarnings("unused")
                boolean created = file.getParentFile().mkdirs();
                Utils.makeFile(SimpleWarp.path);
            }
            warps.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWarps() {
        Object[] objects = SimpleWarp.warpManager.warps.getKeys(false).toArray();
        ArrayList<String> arrayList = new ArrayList<>();
        for (Object i : objects) {
            if (SimpleWarp.warpManager.warps.get((String) i) != null) {
                arrayList.add((String) i);
            }
        }
        return arrayList;
    }
}
