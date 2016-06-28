package com.leviathanstudio.mineide.ui.tool;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.mineide.ui.control.IconButton;

public class ToolCategory
{
    private final String     name;

    private List<IconButton> items;

    public ToolCategory(String name)
    {
        this.name = name;
        items = Lists.newArrayList();
    }

    public ToolCategory add(IconButton mi)
    {
        items.add(mi);
        return this;
    }

    public ToolCategory add(String icon)
    {
        IconButton ib = new IconButton(icon);
        return this.add(ib);
    }

    public String getName()
    {
        return name;
    }

    public List<IconButton> getItems()
    {
        return this.items;
    }
}
