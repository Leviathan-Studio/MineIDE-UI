package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiMain extends Gui
{
    public static StackPane root;

    @Override
    public void init(Stage stage)
    {
        stage.setOnCloseRequest((WindowEvent e) ->
        {
            TabHelper.closeAll();
        });
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        stage.setTitle(Gui.mineIdeInfo.getAppName() + " v" + Gui.mineIdeInfo.getAppVersion() + " " + "Forge "
                + Gui.mineIdeInfo.getForgeVersion());
        root = new StackPane();
        root.setId("mainFrame");
        VBox box = new VBox();
        root.getChildren().add(box);
        Scene scene = new Scene(root, Gui.width, Gui.height);
        scene.getStylesheets().add(MineIDE.class.getResource(Utils.CSS_DIR + "style.css").toString());

        GuiActionBar.init(scene, box);
        GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

        stage.setScene(scene);
        stage.show();
    }
}
