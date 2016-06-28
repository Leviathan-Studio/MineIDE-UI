package com.leviathanstudio.mineide.ui.tool;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.mineide.ui.control.IconButton;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ToolManagement
{
    public static ToolManagement instance;

    public final ToolBar         tooBar;
    public List<Button>          listButton;

    public ToolManagement(ToolBar tooBar)
    {
        this.tooBar = tooBar;
        ToolManagement.instance = this;
        listButton = Lists.newArrayList();
    }

    public void addButton(String icon)
    {
        IconButton ib = new IconButton(icon);
        listButton.add(ib);
        tooBar.getItems().add(ib);
    }

    public void addButton(String name, ToolCategory... menuCats)
    {

        // add menus
        for (ToolCategory tc : menuCats)
        {
            // check if category is not empty
            if (tc.getItems().size() > 0)
            {
                // add a separator
                // if (tooBar.getItems().size() != 0)
                // tooBar.getItems().add(new SeparatorMenuItem());
                // add all menu items
                listButton.addAll(tc.getItems());
                tooBar.getItems().addAll(tc.getItems());
            }
        }
    }
}
