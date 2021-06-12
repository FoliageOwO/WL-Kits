package moe.windleaf.WLKits.Plugins.Disenchant;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.MessageGetter;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Disenchant {
    public static Inventory menu = Bukkit.createInventory(null, 9, Utils.formatColor("&3WL-Kits &a> &bDisenchant 菜单"));
    public static ItemStack disenchantBook;
    public static MessageGetter m = new MessageGetter("Disenchant");
    public static ItemStack confirm;
    public static ItemStack cancel;

    public static void load() {
        registerRecipe();
        Utils.eventRegister(new Events());
    }

    public static void loadInventory(Player player) {
        menu.setItem(3, confirm);
        menu.setItem(4, player.getInventory().getItemInOffHand());
        menu.setItem(5, cancel);
    }

    public static void disenchant(Player player, ItemStack offHand) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        if (meta == null) {
            if (player.isOp()) { Utils.smartSendPrefix(player, m.get("转移失败-管理员"), "Disenchant"); } else { Utils.smartSendPrefix(player, m.get("转移失败-玩家"), "Disenchant"); }
        } else {
            Map<Enchantment, Integer> enchants = offHand.getEnchantments();
            for (Map.Entry<Enchantment,Integer> enchant : enchants.entrySet()) { meta.addStoredEnchant(enchant.getKey(), enchant.getValue(), true); }
            ArrayList<String> lore = new ArrayList<>();
            HashMap<String, String> i = new HashMap<>();
            i.put("playerName", player.getName());
            lore.add(Utils.insert(Utils.formatColor(m.get("物品介绍")), i));
            meta.setLore(lore);
            book.setItemMeta(meta);
            player.getInventory().setItemInMainHand(book);
            HashMap<String, String> z = new HashMap<>();
            z.put("item", player.getInventory().getItemInOffHand().getType().name());
            Utils.smartSendPrefix(player, Utils.insert(m.get("转移成功"), z), "Disenchant");
            player.getInventory().setItemInOffHand(null);
            player.updateInventory();
        }
    }

    public static void registerRecipe() {
        // 确认按钮 (confirm)
        confirm = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta confirmMeta = confirm.getItemMeta();
        assert confirmMeta != null;

        confirmMeta.setDisplayName(Utils.formatColor("&a确认"));
        ArrayList<String> confirmLore = new ArrayList<>();
        confirmLore.add(Utils.formatColor("&a点击以确认转移附魔"));
        confirmMeta.setLore(confirmLore);
        confirm.setItemMeta(confirmMeta);

        // 取消按钮 (cancel)
        cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        assert cancelMeta != null;

        cancelMeta.setDisplayName(Utils.formatColor("&c取消"));
        ArrayList<String> cancelLore = new ArrayList<>();
        cancelLore.add(Utils.formatColor("&c点击以取消"));
        cancelMeta.setLore(cancelLore);
        cancel.setItemMeta(cancelMeta);

        // 附魔书
        disenchantBook = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta bookMeta = disenchantBook.getItemMeta();
        assert bookMeta != null;

        bookMeta.setDisplayName(Utils.formatColor("&5转移附魔书"));
        ArrayList<String> bookLore = new ArrayList<>();
        bookLore.add(Utils.formatColor("&a右键打开转移附魔菜单"));
        bookMeta.setLore(bookLore);
        disenchantBook.setItemMeta(bookMeta);

        // 附魔书配方
        ShapedRecipe disenchantBookRecipe = new ShapedRecipe(new NamespacedKey(Main.I, "disenchantmentbook"), disenchantBook);
        disenchantBookRecipe.shape("###", "#@#", "###");
        disenchantBookRecipe.setIngredient('#', Material.GLOWSTONE_DUST);
        disenchantBookRecipe.setIngredient('@', Material.BOOK);
        Bukkit.addRecipe(disenchantBookRecipe);
    }
}
