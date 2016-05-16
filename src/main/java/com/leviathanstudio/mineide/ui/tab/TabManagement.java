package com.leviathanstudio.mineide.ui.tab;

import java.util.ArrayList;
import java.util.List;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.ui.controls.CloseableTabPane;
import com.leviathanstudio.mineide.ui.controls.DraggableTab;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class TabManagement
{
    public static final List<TabPane> tabPanes = new ArrayList<>();
    
    @Getter
    private final TabPane tabPane;
    
    
    
    public TabManagement(TabPane tabPane)
    {
        this.tabPane = tabPane;
        TabManagement.tabPanes.add(tabPane);
    }
    
    public void addTab(String name, String id, Node content)
    {
        Tab tab = TabHelper.createTabWithContextMenu(name, id, "/mineIDE/img/addIcon.png");
        HBox hbox = new HBox();
        
        hbox.getChildren().add(content);
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        this.tabPane.getTabs().add(tab);
    }
    
    public void closeCurrentTab()
    {
        TabHelper.closeTab(this.tabPane.getSelectionModel().getSelectedItem());
    }
    
    
    
    
    
}
