package com.leviathanstudio.mineide.ui.tool;

import java.util.List;

import com.google.common.collect.Lists;

import javafx.scene.control.MenuItem;

public class ToolCategory
{
    private final String   name;

    private List<MenuItem> items;

    public ToolCategory(String name)
    {
        this.name = name;
        items = Lists.newArrayList();
    }

    public ToolCategory add(MenuItem mi)
    {
        // items.add(mi);
        return this;
    }

    public ToolCategory add(String text)
    {
        // MenuItem mi = new MenuItemTranslate(text);
        return this;// add(mi);
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
