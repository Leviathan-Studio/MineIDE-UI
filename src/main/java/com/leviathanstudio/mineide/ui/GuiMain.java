package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.util.Util;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GuiMain extends Gui
{
    public static StackPane root;

    @Override
    public void init(Stage stage)
    {
        // stage.setOnCloseRequest((WindowEvent e) ->
        // {
        // TabHelper.closeAll();
        // });
        stage.getIcons().add(new Image(Util.IMG_DIR + "icon.png"));
        // stage.setTitle(Gui.mineIdeInfo.getAppName() + " v" +
        // Gui.mineIdeInfo.getAppVersion() + " " + "Forge "
        // + Gui.mineIdeInfo.getForgeVersion());
        GuiMain.root = new StackPane();
        GuiMain.root.setId("mainFrame");
        Scene scene = new Scene(GuiMain.root, Util.BASE_WIDTH, Util.BASE_HEIGHT);
        scene.getStylesheets().add(MineIDE.class.getResource(Util.CSS_DIR + "style.css").toString());

        // c.init(scene, box);
        // GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

        stage.setScene(scene);
        stage.show();
    }
}