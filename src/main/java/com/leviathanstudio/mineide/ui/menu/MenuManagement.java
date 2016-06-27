package com.leviathanstudio.mineide.ui.menu;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.mineide.ui.control.MenuTranslate;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;

public class MenuManagement
{
    public static MenuManagement instance;

    public final MenuBar         menu;
    public List<Menu>            listMenu;

    public MenuManagement(MenuBar menu)
    {
        this.menu = menu;
        MenuManagement.instance = this;
        listMenu = Lists.newArrayList();
    }

    public void addMenu(String name, MenuCategory... menuCats)
    {
        Menu newItem = new MenuTranslate(name);

        // add menus
        for (MenuCategory mc : menuCats)
        {
            // check if category is not empty
            if (mc.getItems().size() > 0)
            {
                // add a separator
                if (newItem.getItems().size() != 0)
                    newItem.getItems().add(new SeparatorMenuItem());
                // add all menu items
                newItem.getItems().addAll(mc.getItems());
            }
        }

        listMenu.add(newItem);
        menu.getMenus().add(newItem);
    }
}
