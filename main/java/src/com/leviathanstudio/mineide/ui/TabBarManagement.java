package com.leviathanstudio.mineide.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.ui.component.DraggableTab;
import com.leviathanstudio.mineide.ui.component.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TabBarManagement
{
    public static final Set<TabPane> tabPanes = new HashSet<>();
    
    private final TabPane tabPane;
    
    public TabBarManagement(TabPane tabPane)
    {
        this.tabPane = tabPane;
    }
    
    public void addTab(String name, String id)
    {
        Tab tab = createTabWithContextMenu(name, id);
        HBox hbox = new HBox();
        CodeEditor editor = new CodeEditor("");
        
        hbox.getChildren().add(editor);
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        tabPane.getTabs().add(tab);
    }
    
    private Tab createTabWithContextMenu(String title, String id, MenuItem... items)
    {
        Tab tab = new DraggableTab(title);
        tab.setId(id);
        
        ContextMenu contextMenu = new ContextMenu(new MenuItem());
        tab.setContextMenu(contextMenu);
        
        Pane content = new Pane();
        
        // Call just before the menu show, use to set the good item in
        contextMenu.setOnShowing((WindowEvent) -> {
            int index = tab.getTabPane().getTabs().indexOf(tab);
            
            // list<MenuItem> get
            List<MenuItem> subItem = getConextMenu(tab.getTabPane(), tab, index);
            // set MenuItem in contextenu
            contextMenu.getItems().clear();
            for(int i = 0; i < subItem.size(); i++)
                contextMenu.getItems().add(subItem.get(i));
            
            // set behavior
            setSubItemBehavior(contextMenu, tab);
        });
        
        content.setOnContextMenuRequested(e -> {
            contextMenu.show(content, e.getScreenX(), e.getScreenY());
        });
        tab.setContent(content);
        
        return tab;
    }
    
    public void closeAll()
    {
        TabBarManagement.tabPanes.clear();
        tabPane.getTabs().clear();
    }
    
    public void closeAllWindows(TabPane tabPane)
    {
        tabPane.getTabs().clear();
    }
    
    public void closeTab(Tab tab)
    {
        if(tab != null)
            tabPane.getTabs().remove(tab);
    }
    
    public void closeCurrentTab()
    {
        closeTab(tabPane.getSelectionModel().getSelectedItem());
    }
    
    private List<MenuItem> getConextMenu(TabPane tabPane, Tab tab, int posTab)
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
        
        if(tabPane.getTabs().size() > 1)
        {
            item.add(closeOther);
            
            if(posTab != 0)
            {
                item.add(closeLeft);
            }
            if(posTab != tabPane.getTabs().size() - 1)
            {
                item.add(closeRight);
            }
            
            item.add(new SeparatorMenuItem());
            item.add(closeAll);
        }
        
        return item;
    }
    
    private void setSubItemBehavior(ContextMenu contextMenu, Tab tab)
    {
        for(MenuItem menus : contextMenu.getItems())
        {
            menus.setOnAction((ActionEvent e) -> {
                MenuItem item = (MenuItem)e.getSource();
                switch(item.getId())
                {
                    case "close":
                        tab.getTabPane().getTabs().remove(tab);
                        break;
                    case "close_other":
                        int index = 0;
                        while(tab.getTabPane().getTabs().size() != 1)
                        {
                            if(tab.getTabPane().getTabs().get(index).getId().equals(tab.getId()))
                                index = 1;
                            tab.getTabPane().getTabs().remove(index);
                        }
                        break;
                    case "close_left":
                        while(tab.getTabPane().getTabs().indexOf(tab) != 0)
                        {
                            tab.getTabPane().getTabs().remove(0);
                        }
                        break;
                    case "close_right":
                        while(tab.getTabPane().getTabs().indexOf(tab) != tab.getTabPane().getTabs().size() - 1)
                        {
                            tab.getTabPane().getTabs().remove(tab.getTabPane().getTabs().size() - 1);
                        }
                        break;
                    case "close_all":
                        
                        closeAllWindows(tab.getTabPane());
                        break;
                }
            });
        }
    }
    
}
