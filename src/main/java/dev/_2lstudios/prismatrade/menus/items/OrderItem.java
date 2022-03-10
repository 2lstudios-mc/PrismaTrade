package dev._2lstudios.prismatrade.menus.items;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev._2lstudios.hypermenus.api.MenuItem;

public class OrderItem extends MenuItem {
    public OrderItem(int amount, Material type, int price) {
        // TODO: Set displayname and lore
        setDisplayname("x" + amount + " " + type + " $" + price);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        // TODO: Cancel order clicked by owner
        // TODO: Buy/Sell depending on type of order
    }
}