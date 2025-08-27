package me.pizza.pizzacp.manager;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.pizza.pizzacp.PizzaCP;
import me.pizza.pizzacp.stat.CPStat;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
import net.Indyuce.mmoitems.stat.data.DoubleData;
import net.Indyuce.mmoitems.stat.data.StringData;

import java.text.DecimalFormat;

import org.bukkit.inventory.ItemStack;

public class ItemManager {

    private final PizzaCP plugin;

    public ItemManager(PizzaCP plugin) {
        this.plugin = plugin;
    }

    public ItemStack applyCp(ItemStack item) {
        return applyCp(NBTItem.get(item));
    }

    public ItemStack applyCp(NBTItem nbt) {
        if (!canApplyCp(nbt)) return nbt.getItem();

        LiveMMOItem mmoitem = new LiveMMOItem(nbt);
        mmoitem.setData(CPStat.COMBAT_POWER, new StringData(calculateItemCp(nbt)));

        return mmoitem.newBuilder().buildSilently();
    }

    public boolean canApplyCp(ItemStack item) {
        return canApplyCp(NBTItem.get(item));
    }

    public boolean canApplyCp(NBTItem nbt) {
        if (!nbt.hasType()) return false;

        String recalculate = calculateItemCp(nbt);
        if (recalculate.equals("0")) return false;

        String current = getItemCp(nbt);
        return !recalculate.equals(current);
    }

    public String calculateItemCp(NBTItem nbt) {
        VolatileMMOItem mmoitem = new VolatileMMOItem(nbt);

        double sum = plugin.getConfigManager().getStatMap().entrySet().stream()
                .filter(entry -> mmoitem.hasData(entry.getKey()))
                .mapToDouble(entry -> ((DoubleData) mmoitem.getData(entry.getKey())).getValue() * entry.getValue())
                .sum();

        return new DecimalFormat("#.#").format(sum);
    }

    public String getItemCp(NBTItem nbt) {
        VolatileMMOItem mmoitem = new VolatileMMOItem(nbt);
        return mmoitem.hasData(CPStat.COMBAT_POWER)
                ? ((StringData) mmoitem.getData(CPStat.COMBAT_POWER)).getString()
                : "0";
    }
}
