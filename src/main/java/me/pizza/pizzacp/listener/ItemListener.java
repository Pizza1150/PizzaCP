package me.pizza.pizzacp.listener;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import me.pizza.pizzacp.manager.ItemManager;
import net.Indyuce.mmoitems.api.event.ItemBuildEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class ItemListener implements Listener {

    private final ItemManager itemManager;

    public ItemListener(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onItemBuild(ItemBuildEvent ev) {
        ItemStack item = ev.getItemStack();
        if (!itemManager.canApplyCp(item)) return;
        ev.setItemStack(itemManager.applyCp(item));
    }

    @EventHandler
    public void onObtain(PlayerInventorySlotChangeEvent ev) {
        ItemStack item = ev.getNewItemStack();
        if (!itemManager.canApplyCp(item)) return;
        ev.getPlayer().getInventory().setItem(ev.getSlot(), itemManager.applyCp(item));
    }
}