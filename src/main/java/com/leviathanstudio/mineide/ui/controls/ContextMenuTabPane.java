package com.leviathanstudio.mineide.ui.controls;

import com.leviathanstudio.mineide.ui.tab.TabHelper;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

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
                
                TabHelper.prepareContextMenu(contextMenu, tab, index);
            }
        });
    }
    
    
    
    
}
