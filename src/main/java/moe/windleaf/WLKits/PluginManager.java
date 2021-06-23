package moe.windleaf.WLKits;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class PluginManager {
    public PluginManager() { super(); }

    public static ArrayList<String> getPlugins() {
        ArrayList<String> plugins = new ArrayList<>();
        plugins.add("AntiCreeper");
        plugins.add("JoinInfo");
        plugins.add("BackDeath");
        plugins.add("PlayerTag");
        plugins.add("SkipNight");
        plugins.add("Suicide");
        plugins.add("SimpleWarp");
        plugins.add("SimpleTpa");
        plugins.add("SimpleBack");
        plugins.add("MineBoard");
        plugins.add("SimpleHome");
        plugins.add("Disenchant");
        plugins.add("SimpleMotd");
        plugins.add("WebSocket");
        return plugins;
    }

    public static ArrayList<String> getMPlugins() {
        ArrayList<String> plugins = new ArrayList<>();
        plugins.add("AntiCreeper");
        plugins.add("SkipNight");
        plugins.add("Disenchant");
        plugins.add("SimpleMotd");
        return plugins;
    }

    @SuppressWarnings("all")
    public void load() {
        for (String pl : getPlugins()) {
            try {
                String name = "moe.windleaf.WLKits.plugins." + pl + "." + pl;
                Util.logInfoPrefix("&f加载子插件 &3" + pl + "&f...");
                Class<?> clazz = Class.forName(name);
                Object object = clazz.newInstance();
                Method method = object.getClass().getDeclaredMethod("load");
                method.invoke(object);
            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    NoSuchMethodException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
