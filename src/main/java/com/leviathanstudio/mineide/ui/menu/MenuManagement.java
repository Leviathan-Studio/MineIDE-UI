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

        // Ajout menus
        for (MenuCategory mc : menuCats)
        {
            if (newItem.getItems().size() != 0)
                newItem.getItems().add(new SeparatorMenuItem());
            newItem.getItems().addAll(mc.getItems());
        }

        listMenu.add(newItem);
    }
}
