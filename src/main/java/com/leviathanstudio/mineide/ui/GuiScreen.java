package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.util.Util;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class GuiScreen extends Gui
{

    public Pane root;

    public GuiScreen(Pane root)
    {
        this.root = root;
    }

    public void addPart(GuiPart part)
    {
        part.init();
        root.getChildren().add(part.root);
    }

    public void show(Stage stage)
    {
        Scene scene = new Scene(this.root, Util.BASE_WIDTH, Util.BASE_HEIGHT);
        scene.getStylesheets().add(MineIDE.class.getResource(Util.CSS_DIR + "style.css").toString());

        stage.setScene(scene);
        stage.show();
    }

}
