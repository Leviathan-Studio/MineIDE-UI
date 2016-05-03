package com.leviathanstudio.mineide.ui.controls;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class CloseableTabPane extends TabPane
{
    private Stage stage;
    
    public CloseableTabPane(Stage stage, Tab... tabs)
    {
        super(tabs);
        this.stage = stage;
        init();
    }
    
    public CloseableTabPane(Stage stage)
    {
        super();
        this.stage = stage;
        init();
    }
    
    public CloseableTabPane()
    {
        super();
        this.stage = null;
        init();
    }
    
    public CloseableTabPane(TabPane pane)
    {
        this((Tab[])pane.getTabs().toArray());
    }
    
    public CloseableTabPane(Tab... tabs)
    {
        this(null, tabs);
    }
    
    private void init()
    {
        ContextMenu contextMenu = new ContextMenu(new MenuItem());
        this.setContextMenu(contextMenu);
        
        // Call just before the menu show, use to set the good item in
        contextMenu.setOnShowing((WindowEvent) -> {
                        
            int index = getSelectedTab(this);
            if(index != -1)
            {
                Tab tab = this.getTabs().get(index);
                
                // list<MenuItem> get
                List<MenuItem> subItem = getContextMenu(tab, index);
                // set MenuItem in contextMenu
                contextMenu.getItems().clear();
                for(int i = 0; i < subItem.size(); i++)
                    contextMenu.getItems().add(subItem.get(i));
                    
                // set behavior
                setSubItemBehavior(contextMenu, tab);
            }
        });
    }
    
    public static int getSelectedTab(TabPane pane)
    {
        for(int i = 0; i < pane.getTabs().size(); i++)
        {
            if(pane.getTabs().get(i).isSelected())
                return i;
        }
        return -1;
    }
    
    public Stage getStage()
    {
        return this.stage;
    }
    
    public void setStage(Stage newStage)
    {
        this.stage = newStage;
    }
    
    public void hide()
    {
        if(this.stage != null)
            this.stage.hide();
    }
    
    private List<MenuItem> getContextMenu(Tab tab, int posTab)
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
        
        if(tab.getTabPane().getTabs().size() > 1)
        {
            item.add(closeOther);
            
            if(posTab != 0)
            {
                item.add(closeLeft);
            }
            if(posTab != tab.getTabPane().getTabs().size() - 1)
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
                        tab.getTabPane().getTabs().clear();
                        break;
                }
            });
        }
    }
}
