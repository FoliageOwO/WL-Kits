package moe.windleaf.WLKits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class YmlConfig {
    public Map<String, Object> ob;
    private final FileConfiguration temp = new YamlConfiguration();
    private final Map<String, Object> h = new HashMap<>();
    private File file;

    public YmlConfig(String path) {
        try {
            file = new File(path);
            if (!file.exists()) { Util.makeFile(path); }
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            Yaml yaml = new Yaml();
            ob = yaml.load(br);
            for (String k : ob.keySet()) { h.put(k, h.get(k)); }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String key, Object value) { h.put(key, value); }

    public void save() {
        try {
            for (String k : h.keySet()) { temp.set(k, h.get(k)); }
            temp.save(file);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void setAndSave(String key, Object value) { set(key, value); save(); }

    public Object get(String key) { return h.get(key); }
}
