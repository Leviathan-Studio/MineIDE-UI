package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiMain extends Gui
{
    public static void init(Stage stage)
    {
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        stage.setTitle(Gui.mineIdeInfo.getAppName() + " v" + Gui.mineIdeInfo.getAppVersion() + " " + "Forge "
                + Gui.mineIdeInfo.getForgeVersion());
        VBox root = new VBox();
        root.setId("mainFrame");
        Scene scene = new Scene(root, Gui.width, Gui.height);
        scene.getStylesheets().add(MineIDE.class.getResource(Utils.CSS_DIR + "style.css").toString());

        GuiActionBar.init(scene);
        GuiJavaEditor.init(scene);
        GuiConsole.init(scene);

        stage.setScene(scene);
        stage.show();
    }
}
