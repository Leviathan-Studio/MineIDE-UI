package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.controls.ContextMenuTabPane;
import com.leviathanstudio.mineide.ui.tab.TabConsole;
import com.leviathanstudio.mineide.ui.tab.TabManagement;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GuiJavaEditor
{

    public static void init(Scene scene, VBox box)
    {

        final VBox layout = new VBox();
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 30;");

        TabPane tabPane = new ContextMenuTabPane();

        TabManagement.instance = new TabManagement(tabPane);

        BorderPane borderPane = new BorderPane();

        TabManagement.instance.addTab("Main.java", "main", new TextArea());
        TabManagement.instance.addTab("Test.java", "test", null);
        TabManagement.instance.addTab("Console", "console", new TabConsole());

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(tabPane);

        box.getChildren().addAll(borderPane);

        MineIDE.primaryStage.setScene(scene);
        MineIDE.primaryStage.show();
    }
}
