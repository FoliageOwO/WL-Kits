package moe.windleaf.WLKits;

import java.util.Map;

public class MessageGetter {
    protected String name;

    public MessageGetter(String pluginName) { name = pluginName; }

    public String get(String path) {
        @SuppressWarnings("unchecked") Map<String, String> sub = (Map<String, String>) Main.messages.get(name);
        return sub.get(path);
    }
}
