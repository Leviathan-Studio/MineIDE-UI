package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.main.MineIDE;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GuiJavaEditor
{
    private static CodeEditor editor;
    public static TabBarManagement tabBar;
    
    public static CodeEditor getEditor()
    {
        return editor;
    }
    
    public static void init(Scene scene)
    {
        editor = new CodeEditor("");
        
        final VBox layout = new VBox();
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 30;");
        
        TabPane tabPane = new TabPane();
        
        tabBar = new TabBarManagement(tabPane);
        
        BorderPane borderPane = new BorderPane();
        
        tabBar.addTab("Main.java", "main");
        tabBar.addTab("Test.java", "test");
        
        /*
         * Tab tab = new Tab(); tab.setText("Main.java"); HBox hbox = new HBox(); hbox.getChildren().addAll(editor); hbox.setAlignment(Pos.CENTER); tab.setContent(hbox); tabPane.getTabs().add(tab);
         */
        
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        
        ((VBox)scene.getRoot()).getChildren().addAll(borderPane);
        
        MineIDE.primaryStage.setScene(scene);
        MineIDE.primaryStage.show();
    }
}
