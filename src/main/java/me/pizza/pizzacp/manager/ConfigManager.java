package me.pizza.pizzacp.manager;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import me.pizza.pizzacp.PizzaCP;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.Indyuce.mmoitems.stat.type.ItemStat;

public class ConfigManager {

    private final PizzaCP plugin;

    private final Map<ItemStat<?, ?>, Double> statMap = new HashMap<>();

    public ConfigManager(PizzaCP plugin) {
        this.plugin = plugin;
        reload(null);
    }

    public void reload(CommandSender sender) {
        statMap.clear();

        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) plugin.saveDefaultConfig();

        plugin.reloadConfig();

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("stats");
        if (section == null) return;

        for (String key : section.getKeys(false)) {
            String statId = key.toUpperCase().replace("-", "_");
            ItemStat<?, ?> stat = MMOItems.plugin.getStats().get(statId);

            if (stat == null) {
                if (sender != null) sender.sendMessage("§cCannot register stat " + statId);
                continue;
            }

            if (!(stat instanceof DoubleStat)) {
                if (sender != null) sender.sendMessage("§cStat " + statId + " is not a number stat!");
                continue;
            }

            Double value = section.getDouble(key);
            statMap.put(stat, value);
        }

        if (sender != null) sender.sendMessage("§aPizzaCP reloaded");
    }

    public Map<ItemStat<?, ?>, Double> getStatMap() {
        return Collections.unmodifiableMap(statMap);
    }
}