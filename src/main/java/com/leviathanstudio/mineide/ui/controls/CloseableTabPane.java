package com.leviathanstudio.mineide.ui.controls;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class CloseableTabPane extends ContextMenuTabPane
{
    private Stage stage;
    
    public CloseableTabPane(Stage stage, Tab... tabs)
    {
        super(tabs);
        this.stage = stage;
    }
    
    public CloseableTabPane(Stage stage)
    {
        super();
        this.stage = stage;
    }
    
    public CloseableTabPane()
    {
        super();
        this.stage = null;
    }
    
    public CloseableTabPane(TabPane pane)
    {
        this((Tab[])pane.getTabs().toArray());
    }
    
    public CloseableTabPane(Tab... tabs)
    {
        this(null, tabs);
    }
    
    public static int getSelectedTab(TabPane pane)
    {
        for(int i = 0; i < pane.getTabs().size(); i++)
            if(pane.getTabs().get(i).isSelected())
                return i;
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
    
}
