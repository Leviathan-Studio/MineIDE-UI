package com.leviathanstudio.mineide.ui.tool;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.mineide.ui.control.MenuTranslate;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;

public class ToolManagement
{
    public static ToolManagement instance;

    public final MenuBar         menu;
    public List<Menu>            listMenu;

    public ToolManagement(MenuBar menu)
    {
        this.menu = menu;
        ToolManagement.instance = this;
        listMenu = Lists.newArrayList();
    }

    public void addMenu(String name, ToolCategory... menuCats)
    {
        Menu newItem = new MenuTranslate(name);

        // add menus
        for (ToolCategory tc : menuCats)
        {
            // check if category is not empty
            if (tc.getItems().size() > 0)
            {
                // add a separator
                if (newItem.getItems().size() != 0)
                    newItem.getItems().add(new SeparatorMenuItem());
                // add all menu items
                newItem.getItems().addAll(tc.getItems());
            }
        }

        listMenu.add(newItem);
        menu.getMenus().add(newItem);
    }
}
