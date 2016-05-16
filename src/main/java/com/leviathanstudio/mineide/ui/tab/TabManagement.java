package com.leviathanstudio.mineide.ui.tab;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class TabManagement
{
    public static TabManagement instance; 
    
    public final List<TabPane> tabPanes = new ArrayList<>();
   
    public TabManagement(TabPane tabPane)
    {
        this.tabPanes.add(tabPane);
        instance = this;
    }
    
    public void addTab(String name, String id, Node content)
    {
        Tab tab = TabHelper.createTabWithContextMenu(name, id, "/mineIDE/img/addIcon.png");
        HBox hbox = new HBox();
        
        hbox.getChildren().add(content);
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        this.tabPanes.get(0).getTabs().add(tab);
    }
    
    public void closeCurrentTab()
    {
        TabHelper.closeTab(this.tabPanes.get(0).getSelectionModel().getSelectedItem());
    }
    
    
    
    
    
}
