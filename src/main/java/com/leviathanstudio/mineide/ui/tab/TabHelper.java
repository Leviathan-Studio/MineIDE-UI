package com.leviathanstudio.mineide.ui.tab;

import java.util.ArrayList;
import java.util.List;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.ui.GuiJavaEditor;
import com.leviathanstudio.mineide.ui.controls.CloseableTabPane;
import com.leviathanstudio.mineide.ui.controls.DraggableTab;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

public class TabHelper
{
    public static Tab createTabWithContextMenu(String title, String id, String iconPath)
    {
        Tab tab = new DraggableTab(title, iconPath);
        tab.setId(id);

        addContextMenu(tab);

        return tab;
    }

    public static void addContextMenu(Tab tab)
    {
        ContextMenu contextMenu = new ContextMenu(new MenuItem());
        tab.setContextMenu(contextMenu);

        Pane content = new Pane();

        // Call just before the menu show, use to set the good item in
        contextMenu.setOnShowing((WindowEvent) ->
        {
            int index = tab.getTabPane().getTabs().indexOf(tab);

            prepareContextMenu(contextMenu, tab, index);
        });

        tab.setContent(content);
    }

    public static void prepareContextMenu(ContextMenu contextMenu, Tab tab, int index)
    {
        // list<MenuItem> get
        List<MenuItem> subItem = TabHelper.getContextMenu(tab, index);
        // set MenuItem in contextMenu
        contextMenu.getItems().clear();
        for (int i = 0; i < subItem.size(); i++)
            contextMenu.getItems().add(subItem.get(i));

        // set behavior
        TabHelper.setSubItemBehavior(contextMenu, tab);
    }

    public static void setSubItemBehavior(ContextMenu contextMenu, Tab tab)
    {
        for (MenuItem menus : contextMenu.getItems())
            menus.setOnAction((ActionEvent e) ->
            {
                MenuItem item = (MenuItem) e.getSource();
                switch (item.getId())
                {
                    case "close":
                        tab.getTabPane().getTabs().remove(tab);
                        break;
                    case "close_other":
                        int index = 0;
                        while (tab.getTabPane().getTabs().size() != 1)
                        {
                            if (tab.getTabPane().getTabs().get(index).getId().equals(tab.getId()))
                                index = 1;
                            tab.getTabPane().getTabs().remove(index);
                        }
                        break;
                    case "close_left":
                        while (tab.getTabPane().getTabs().indexOf(tab) != 0)
                            tab.getTabPane().getTabs().remove(0);
                        break;
                    case "close_right":
                        while (tab.getTabPane().getTabs().indexOf(tab) != tab.getTabPane().getTabs().size() - 1)
                            tab.getTabPane().getTabs().remove(tab.getTabPane().getTabs().size() - 1);
                        break;
                    case "close_all":
                        closeAllWindows(tab.getTabPane());
                        break;
                }
            });
    }

    public static List<MenuItem> getContextMenu(Tab tab, int posTab)
    {
        MenuItem close = new MenuItemTranslate("menu.tab.item.close");
        MenuItem closeOther = new MenuItemTranslate("menu.tab.item.closeOther");
        MenuItem closeLeft = new MenuItemTranslate("menu.tab.item.closeLeft");
        MenuItem closeRight = new MenuItemTranslate("menu.tab.item.closeRight");
        MenuItem closeAll = new MenuItemTranslate("menu.tab.item.closeAll");

        close.setId("close");
        closeOther.setId("close_other");
        closeLeft.setId("close_left");
        closeRight.setId("close_right");
        closeAll.setId("close_all");

        ArrayList<MenuItem> item = new ArrayList<MenuItem>();

        item.add(close);

        if (tab.getTabPane().getTabs().size() > 1)
        {
            item.add(closeOther);

            if (posTab != 0)
                item.add(closeLeft);
            if (posTab != tab.getTabPane().getTabs().size() - 1)
                item.add(closeRight);

            item.add(new SeparatorMenuItem());
            item.add(closeAll);
        }

        return item;
    }

    public static void closeAll()
    {
        int i = 0;
        while (i < TabManagement.tabPanes.size())
        {
            TabPane pane = TabManagement.tabPanes.get(i);
            int j = 0;
            while (j < pane.getTabs().size())
            {
                Tab tab = pane.getTabs().get(j);
                if (tab.getContent() instanceof CodeEditor)
                {
                    tab.setClosable(true);
                    pane.getTabs().remove(j);
                }
                else
                    j++;
            }
            if (pane.getTabs().size() > 0)
            {
                TabManagement.tabPanes.remove(i);
                if (pane instanceof CloseableTabPane)
                    ((CloseableTabPane) pane).hide();
            }
            else
                i++;
        }
        GuiJavaEditor.tabBar.getTabPane().getTabs().clear();
    }

    public static int getSelectedTab(TabPane pane)
    {
        for (int i = 0; i < pane.getTabs().size(); i++)
            if (pane.getTabs().get(i).isSelected())
                return i;
        return -1;
    }

    public static void closeAllWindows(TabPane tabPane)
    {
        tabPane.getTabs().clear();
    }

    public static void closeTab(Tab tab)
    {
        if (tab != null)
            tab.getTabPane().getTabs().remove(tab);
    }
}