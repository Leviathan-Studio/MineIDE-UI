package fr.zeamateis.mineide.ui;

import fr.zeamateis.mineide.editor.CodeEditor;
import fr.zeamateis.mineide.main.MineIDE;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GuiJavaEditor
{
    private static CodeEditor editor;
    
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
        
        BorderPane borderPane = new BorderPane();
        
        Tab tab = new Tab();
        tab.setText("Main.java");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(editor);
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        tabPane.getTabs().add(tab);
        
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(tabPane);
        
        ((VBox)scene.getRoot()).getChildren().addAll(borderPane);
        
        MineIDE.primaryStage.setScene(scene);
        MineIDE.primaryStage.show();
    }
}
