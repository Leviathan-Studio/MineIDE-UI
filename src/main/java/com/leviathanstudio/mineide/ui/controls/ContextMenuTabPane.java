package com.leviathanstudio.mineide.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.leviathanstudio.mineide.ui.TabHelper;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ContextMenuTabPane extends TabPane
{
    public ContextMenuTabPane(Tab... tabs)
    {
        super(tabs);
        this.init();
    }
    
    public ContextMenuTabPane()
    {
        super();
        this.init();
    }
    
    private void init()
    {
        
        ContextMenu contextMenu = new ContextMenu(new MenuItem());
        this.setContextMenu(contextMenu);
        
        // Call just before the menu show, use to set the good item in
        contextMenu.setOnShowing((WindowEvent) -> {
            
            int index = TabHelper.getSelectedTab(this);
            if(index != -1)
            {
                Tab tab = this.getTabs().get(index);
                
                // list<MenuItem> get
                List<MenuItem> subItem = TabHelper.getContextMenu(tab, index);
                // set MenuItem in contextMenu
                contextMenu.getItems().clear();
                for(int i = 0; i < subItem.size(); i++)
                    contextMenu.getItems().add(subItem.get(i));
                
                // set behavior
                TabHelper.setSubItemBehavior(contextMenu, tab);
            }
        });
    }
    
    
    
    
}
