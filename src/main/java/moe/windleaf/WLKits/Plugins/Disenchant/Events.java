package moe.windleaf.WLKits.Plugins.Disenchant;

import moe.windleaf.WLKits.Main;
import moe.windleaf.WLKits.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Events implements Listener {
    @EventHandler
    public void _PlayerInteractEvent(PlayerInteractEvent event) {
        if (Main.config().getBoolean("enable-disenchant")) {
            Player player = event.getPlayer();
            ItemStack mainhand = event.getItem();
            if (event.getHand() == EquipmentSlot.HAND) {
                if (mainhand != null) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        ItemStack offhand = player.getInventory().getItemInOffHand();

                        if (mainhand.equals(Disenchant.disenchantBook) & offhand.equals(Disenchant.disenchantBook)) {
                            Utils.smartSendPrefix(player, Disenchant.m.get("附魔书转附魔书"), "Disenchant");
                        } else {
                            if (mainhand.equals(Disenchant.disenchantBook)) {
                                if (mainhand.getType().isAir() || offhand.getType().isAir()) {
                                    Utils.smartSendPrefix(player, Disenchant.m.get("物品为空"), "Disenchant");
                                } else {
                                    if (offhand.getType() == Material.ENCHANTED_BOOK || mainhand.getType() != Material.ENCHANTED_BOOK) {
                                        Utils.smartSendPrefix(player, Disenchant.m.get("附魔书转附魔书"), "Disenchant");
                                    } else {
                                        if (offhand.equals(Disenchant.disenchantBook)) {
                                            Utils.smartSendPrefix(player, Disenchant.m.get("副手不能放附魔书"), "Disenchant");
                                        } else {
                                            if (offhand.getEnchantments().size() == 0 || !offhand.hasItemMeta()) {
                                                Utils.smartSendPrefix(player, Disenchant.m.get("物品没有附魔"), "Disenchant");
                                            } else {
                                                Disenchant.loadInventory(player);
                                                player.openInventory(Disenchant.menu);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void _InventoryClickEvent(InventoryClickEvent event) {
        if (Main.config().getBoolean("enable-disenchant")) {
            Inventory inventory = event.getInventory();
            if (inventory.equals(Disenchant.menu)) {
                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();
                if (Objects.equals(clickedItem, Disenchant.confirm)) {
                    // 确认
                    event.setCancelled(true);
                    player.closeInventory();
                    Disenchant.disenchant(player, player.getInventory().getItemInOffHand());
                    player.getInventory().remove(Disenchant.confirm);
                    player.updateInventory();
                }
                if (Objects.equals(clickedItem, Disenchant.cancel)) {
                    // 取消
                    event.setCancelled(true);
                    player.closeInventory();
                    player.getInventory().remove(Disenchant.cancel);
                    player.updateInventory();
                }
            }
        }
    }
}
