package me.pizza.pizzacp.listener;

import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import me.pizza.pizzacp.manager.ItemManager;
import net.Indyuce.mmoitems.api.event.ItemBuildEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    private final ItemManager itemManager;

    public ItemListener(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @EventHandler
    public void onItemBuild(ItemBuildEvent ev) {
        ItemStack item = ev.getItemStack();
        if (!itemManager.canApply(item)) return;
        ev.setItemStack(itemManager.apply(item));
        Bukkit.broadcastMessage("Apply from ItemBuildEvent");
    }

    @EventHandler
    public void onObtain(PlayerInventorySlotChangeEvent ev) {
        ItemStack item = ev.getNewItemStack();
        if (!itemManager.canApply(item)) return;
        ev.getPlayer().getInventory().setItem(ev.getSlot(), itemManager.apply(item));
        Bukkit.broadcastMessage("Apply from ObtainItemEvent");
    }
}