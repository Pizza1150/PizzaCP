package me.pizza.pizzacp.stat;

import org.bukkit.Material;

import net.Indyuce.mmoitems.stat.type.ItemStat;
import net.Indyuce.mmoitems.stat.type.StringStat;

public class CPStat {

    public static final ItemStat<?, ?> COMBAT_POWER = new StringStat(
            "COMBAT_POWER",
            Material.MACE,
            "Combat Power",
            new String[] {"The combat power of the item"},
            new String[] {"!consumable", "!miscellaneous", "all"}
    );
}
