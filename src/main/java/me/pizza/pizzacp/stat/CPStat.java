package me.pizza.pizzacp.stat;

import net.Indyuce.mmoitems.stat.type.ItemStat;
import net.Indyuce.mmoitems.stat.type.StringStat;

import org.bukkit.Material;

public class CPStat {

    public static final ItemStat COMBAT_POWER = new StringStat(
            "COMBAT_POWER",
            Material.MACE,
            "Combat Power",
            new String[] {"The combat power of the item"},
            new String[] {"!consumable", "!miscellaneous", "all"}
    );
}
