package moe.windleaf.WLKits.Plugins.RecipeAdder;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class RecipeManager {
    public HashMap<String, String> charFormat = new HashMap<>();
    public ArrayList<String> loadedRecipes = new ArrayList<>();
    public FileConfiguration recipeConfiguration = new YamlConfiguration();

    public void loadRecipe(CommandSender player, String name) {
        if (!(player instanceof Player)) {
            Utils.mustPlayer(player);
        } else {
            String path = RecipeAdder.prefixPath + File.separator + name + ".yml";
            File recipeFile = new File(path);
            try {
                // 加载 recipeFile
                recipeConfiguration.load(recipeFile);

                // to: 合成后的东西
                String to = recipeConfiguration.getString("to");
                assert to != null;

                // amount: 合成后的物品数量
                int amount = recipeConfiguration.getInt("amount");

                if (recipeConfiguration.getBoolean("shape")) {
                    // 有序合成 (ShapeRecipe)

                    // 将 yml 中 `recipe` 转换为 List<String> recipe
                    @SuppressWarnings("unchecked") List<String> recipe = (List<String>) recipeConfiguration.getList("recipe");

                    // 将 yml 中 `format` 内容转换为 HashMap<String, String> charFormat
                    List<String> chars = new ArrayList<>();
                    assert recipe != null;
                    for (String i : recipe) { for (int d = 0; d < i.length(); d++) { chars.add(String.valueOf(i.charAt(d))); } }
                    Object[] tmp = chars.stream().distinct().toArray();
                    List<String> charsDistinct = new ArrayList<>();
                    for (Object i : tmp) { charsDistinct.add(i.toString()); }
                    for (String i : charsDistinct) { charFormat.put(i, recipeConfiguration.getString("format." + i)); }

                    // 开始加载配方
                    ShapedRecipe shapedRecipe = new ShapedRecipe(
                            new NamespacedKey(Main.I, name),
                            new ItemStack(Objects.requireNonNull(Material.getMaterial(to)), amount)
                    ).shape(recipe.get(0), recipe.get(1), recipe.get(2));
                    for (String i : charFormat.values()) {
                        try {shapedRecipe.setIngredient(getKeyByValue(charFormat, i).charAt(0), Objects.requireNonNull(Material.getMaterial(i)));
                        } catch (NullPointerException e) {
                            cannotLoad((Player) player, String.format("&7未知的物品: &6%s&7!", i));
                            return;
                        }
                        charFormat.remove(i);
                    }
                    Bukkit.addRecipe(shapedRecipe);
                    loadedRecipes.add(name);
                    Utils.sendPrefix(player, String.format("&a成功加载配方 &6%s.yml&a!", name));
                    Utils.broadcastPlayersPrefix(String.format("&a玩家 &6%s &a加载了一个自定义有序配方: &7%s&a.", player.getName(), name));
                } else {
                    // 无序合成 (ShapelessRecipe)

                    // 将 yml 中 `requires` 转换为 List<String> requires
                    @SuppressWarnings("unchecked") List<String> requires = (List<String>) recipeConfiguration.getList("requires");

                    // 开始加载配方
                    assert requires != null;
                    ShapelessRecipe shapelessRecipe = new ShapelessRecipe(
                            new NamespacedKey(Main.I, name),
                            new ItemStack(Objects.requireNonNull(Material.getMaterial(to)), amount)
                    );
                    for (String i : requires) {
                        try {
                            shapelessRecipe.addIngredient(
                                    recipeConfiguration.getInt("count." + i),
                                    Objects.requireNonNull(Material.getMaterial(i)));
                        } catch (NullPointerException e) {
                            cannotLoad((Player) player, String.format("&7未知的物品: &6%s&7!", i));
                            return;
                        }
                    }
                    Bukkit.addRecipe(shapelessRecipe);
                    loadedRecipes.add(name);
                    Utils.sendPrefix(player, String.format("&a成功加载配方 &6%s.yml&a!", name));
                    Utils.broadcastPlayersPrefix(String.format("&a玩家 &6%s &a加载了一个自定义无序配方: &7%s&a.", player.getName(), name));
                }
            } catch (IOException | InvalidConfigurationException | NullPointerException e) {
                Utils.sendPrefix(player, String.format("&c导入配方配置文件 &6%s.yml &c失败: &c&l %s", name, e.getClass().getName()));
                e.printStackTrace();
            }
        }
    }

    public void removeRecipe(CommandSender player, String name) {
        if (loadedRecipes.contains(name)) {
            loadedRecipes.remove(name);
            Bukkit.removeRecipe(new NamespacedKey(Main.I, name));
            Utils.sendPrefix(player, String.format("&a删除配方 &6%s &a成功!", name));
            Utils.broadcastPlayersPrefix(String.format("&c玩家 &6%s &c删除了配方: &7%s&c.", player.getName(), name));
        } else {
            Utils.sendPrefix(player, String.format("&c配方 &6%s &c不存在!", name));
        }
    }

    public void listRecipe(CommandSender player) {
        if (loadedRecipes.size() > 0) {
            Utils.sendPrefix(player, String.valueOf(loadedRecipes));
        } else {
            Utils.sendPrefix(player, "&a没有配方被加载, 快去自定义一个配方吧!");
        }
    }

    private void cannotLoad(Player player, String reason) {
        Utils.sendPrefix(player, String.format("&c加载配方失败, 原因: &7%s", reason));
    }

    public String getKeyByValue(Map<?, ?> map, String value) {
        Set<?> keys = map.keySet();
        for (Object i : keys) { if (map.get(i.toString()) == value) { return i.toString(); } }
        return null;
    }
}
