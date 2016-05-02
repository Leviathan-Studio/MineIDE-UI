package com.leviathanstudio.mineide.ui.component;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class CloseableTabPane extends TabPane
{
    private Stage stage;
    
    public CloseableTabPane(Stage stage, Tab... tabs) {
        super(tabs);
        this.stage = stage;
    }
    
    public CloseableTabPane(TabPane pane) {
        this((Tab[])pane.getTabs().toArray());
    }
    
    public CloseableTabPane(Tab... tabs) {
        this(null, tabs);
    }
    
    public CloseableTabPane(Stage stage) {
        super();
        this.stage = stage;
    }
    
    public CloseableTabPane() {
        super();
        this.stage = null;
    }

    public Stage getStage() {
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
