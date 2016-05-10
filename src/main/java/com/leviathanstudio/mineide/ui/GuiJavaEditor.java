package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.controls.ContextMenuTabPane;
import com.leviathanstudio.mineide.ui.tab.TabConsole;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GuiJavaEditor
{
    private static CodeEditor editor;
    public static TabManagement tabBar;
    
    public static CodeEditor getEditor()
    {
        return GuiJavaEditor.editor;
    }
    
    public static void init(Scene scene)
    {
        GuiJavaEditor.editor = new CodeEditor("");
        
        final VBox layout = new VBox();
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 30;");
        
        TabPane tabPane = new ContextMenuTabPane();
        
        GuiJavaEditor.tabBar = new TabManagement(tabPane);
        
        BorderPane borderPane = new BorderPane();
        
        GuiJavaEditor.tabBar.addTab("Main.java", "main", new CodeEditor(""));
        GuiJavaEditor.tabBar.addTab("Test.java", "test", new CodeEditor(""));
        GuiJavaEditor.tabBar.addTab("Console", "console", new TabConsole());
        
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        
        ((VBox)scene.getRoot()).getChildren().addAll(borderPane);
        
        MineIDE.primaryStage.setScene(scene);
        MineIDE.primaryStage.show();
    }
}
