package com.leviathanstudio.mineide.ui.menu;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.mineide.ui.control.MenuItemTranslate;

import javafx.scene.control.MenuItem;

public class MenuCategory
{
    private final String   name;

    private List<MenuItem> items;

    public MenuCategory(String name)
    {
        this.name = name;
        items = Lists.newArrayList();
    }

    public MenuCategory add(MenuItem mi)
    {
        items.add(mi);
        return this;
    }

    public MenuCategory add(String text)
    {
        MenuItem mi = new MenuItemTranslate(text);
        return add(mi);
    }

    public String getName()
    {
        return name;
    }

    public List<MenuItem> getItems()
    {
        return this.items;
    }
}
