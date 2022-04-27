package dev._2lstudios.prismatrade.interfaces.menus;

import dev._2lstudios.interfacemaker.interfaces.InterfaceMenu;

public class PageMenu extends InterfaceMenu {
    private int page;

    public PageMenu(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
