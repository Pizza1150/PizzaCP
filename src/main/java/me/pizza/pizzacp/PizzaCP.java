package me.pizza.pizzacp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.pizza.pizzacp.command.CoreCommand;
import me.pizza.pizzacp.listener.ItemListener;
import me.pizza.pizzacp.manager.ConfigManager;
import me.pizza.pizzacp.manager.ItemManager;
import me.pizza.pizzacp.stat.CPStat;
import net.Indyuce.mmoitems.MMOItems;

public final class PizzaCP extends JavaPlugin {

    @Getter
    private static PizzaCP plugin;

    @Getter
    private ConfigManager configManager;

    @Getter
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
    
        configManager = new ConfigManager(this);
        itemManager = new ItemManager(this);

        getServer().getPluginManager().registerEvents(new ItemListener(itemManager), this);

        getCommand("pizzacp").setExecutor(new CoreCommand(this));

        // Register MMOItems stat
        if (!MMOItems.plugin.getStats().has(CPStat.COMBAT_POWER.getId())) MMOItems.plugin.getStats().register(CPStat.COMBAT_POWER);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mi reload all");
    }
}