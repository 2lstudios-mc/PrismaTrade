package dev._2lstudios.prismatrade.interfaces.menus;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

import dev._2lstudios.interfacemaker.interfaces.InterfaceItem;
import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;
import dev._2lstudios.interfacemaker.interfaces.contexts.MenuBuildContext;
import dev._2lstudios.prismatrade.PrismaTradeAPI;
import dev._2lstudios.prismatrade.entities.SellOrder;
import dev._2lstudios.prismatrade.interfaces.items.BackItem;
import dev._2lstudios.prismatrade.interfaces.items.PageItem;
import dev._2lstudios.prismatrade.interfaces.items.PageType;
import dev._2lstudios.prismatrade.interfaces.items.SellEntryItem;
import dev._2lstudios.prismatrade.utils.Placeholder;

public class SellMenu extends PageMenu {
    private static int MAX_ENTRY_COUNT = 21;

    private Configuration config;
    private PrismaTradeAPI prismaTrade;
    private Material material;
    private InterfaceMenu lastMenu;
    private int skipAmount;

    public SellMenu(int page, Configuration config, PrismaTradeAPI prismaTrade, Material material,
            InterfaceMenu lastMenu) {
        super(page);
        this.config = config;
        this.prismaTrade = prismaTrade;
        this.material = material;
        this.lastMenu = lastMenu;
        this.skipAmount = (page - 1) * MAX_ENTRY_COUNT;
        setRows(6);
    }

    @Override
    public void onBuild(MenuBuildContext context) {
        SellOrder[] dataFound = prismaTrade.getSellOrders(material, skipAmount,
                MAX_ENTRY_COUNT + 1);
        InterfaceItem[] orderItems = new InterfaceItem[MAX_ENTRY_COUNT];

        context.setTitle(Placeholder.color(config.getString("menu.sell.title")
                .replace("%page%", String.valueOf(getPage())).replace("%type%", "SELL")));

        for (int i = 0; i < MAX_ENTRY_COUNT && i < dataFound.length; i++) {
            SellOrder data = dataFound[i];
            InterfaceItem item = new SellEntryItem(config, data, prismaTrade);

            orderItems[i] = item;
        }

        context.fill(1, orderItems);

        int page = getPage();

        if (page > 1) {
            context.setItem(45, new PageItem(config, new SellMenu(page - 1, config, prismaTrade, material, lastMenu),
                    PageType.PREVIOUS));
        } else {
            context.setItem(45, new BackItem(config, lastMenu));
        }

        if (dataFound.length > MAX_ENTRY_COUNT) {
            context.setItem(53, new PageItem(config, new SellMenu(page + 1, config, prismaTrade, material, lastMenu),
                    PageType.NEXT));
        }
    }
}
