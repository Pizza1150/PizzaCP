package me.pizza.pizzacp.api.event;

import lombok.Getter;
import lombok.Setter;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ApplyCpEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    @Setter
    private String value;

    @Getter
    private final LiveMMOItem item;

    public ApplyCpEvent(LiveMMOItem item, String value) {
        this.item = item;
        this.value = value;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
