package fr.zeamateis.mineide.ui;

import fr.zeamateis.mineide.editor.CodeEditor;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TabBarManagement
{
    private final TabPane tabPane;
    
    public TabBarManagement(TabPane tabPane)
    {
        this.tabPane = tabPane;
    }
    
    public void addTab(String name, String id)
    {
        MenuItem close = new MenuItem("Close");
        MenuItem closeOther = new MenuItem("Close Others");
        MenuItem closeLeft = new MenuItem("Close Tabs to the Left");
        MenuItem closeRight = new MenuItem("Close Tabs to the Right");
        MenuItem closeAll = new MenuItem("Close All");

        close.setId("close");
        closeOther.setId("close_other");
        closeLeft.setId("close_left");
        closeRight.setId("close_right");
        closeAll.setId("close_all");

        
        Tab tab = createTabWithContextMenu(name, id, close, closeOther, closeLeft, closeRight, new SeparatorMenuItem(), closeAll);
        HBox hbox = new HBox();
        CodeEditor editor = new CodeEditor("");
        
        
        hbox.getChildren().add(editor);
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        tabPane.getTabs().add(tab);
    }
    
    private Tab createTabWithContextMenu(String title, String id, MenuItem... items)
    {
        Tab tab = new Tab(title);
        tab.setId(id);
        
        ContextMenu contextMenu = new ContextMenu(items);
        
        for(MenuItem menus : contextMenu.getItems())
        {
            menus.setOnAction((ActionEvent e) -> {
                MenuItem item = (MenuItem)e.getSource();
                switch(item.getId())
                {
                    case "close":
                        tabPane.getTabs().remove(tab);
                        break;
                    case "close_other":
                        int index = 0;
                        while(tabPane.getTabs().size() != 1)
                        {
                            if(tabPane.getTabs().get(index).getId().equals(tab.getId()))
                                index = 1;
                            tabPane.getTabs().remove(index);
                        }
                        break;
                    case "close_left":
                        while(tabPane.getTabs().indexOf(tab) != 0)
                        {
                            tabPane.getTabs().remove(0);
                        }
                        break;
                    case "close_right":
                        while(tabPane.getTabs().indexOf(tab) != tabPane.getTabs().size()-1)
                        {
                            tabPane.getTabs().remove(tabPane.getTabs().size()-1);
                        }
                        break;
                    case "close_all":
                        tabPane.getTabs().clear();
                        break;
                }
            });
        }
        
        tab.setContextMenu(contextMenu);
        
        Pane content = new Pane();
        content.setOnContextMenuRequested(e -> contextMenu.show(content, e.getScreenX(), e.getScreenY()));
        tab.setContent(content);
        
        return tab;
    }
}