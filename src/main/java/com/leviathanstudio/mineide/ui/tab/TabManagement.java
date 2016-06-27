package com.leviathanstudio.mineide.ui.tab;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class TabManagement
{
    public static TabManagement instance;

    public final List<TabPane>  tabPanes = new ArrayList<>();

    public TabManagement(TabPane tabPane)
    {
        this.tabPanes.add(tabPane);
        TabManagement.instance = this;
    }

    public void addTab(String name, String id, Node content)
    {
        Tab tab = TabHelper.createTabWithContextMenu(name, id, "/mineIDE/img/addIcon.png");
        BorderPane borderPane = new BorderPane();
        tab.setContent(borderPane);

        if (content != null)
            borderPane.setCenter(content);
        this.tabPanes.get(0).getTabs().add(tab);
    }

    public void closeCurrentTab()
    {
        TabHelper.closeTab(this.tabPanes.get(0).getSelectionModel().getSelectedItem());
    }

}