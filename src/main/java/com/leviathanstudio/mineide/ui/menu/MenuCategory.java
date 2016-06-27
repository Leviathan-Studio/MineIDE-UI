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

    public void add(MenuItem mi)
    {
        items.add(mi);
    }

    public void add(String text)
    {
        MenuItem mi = new MenuItemTranslate(text);
        add(mi);
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
