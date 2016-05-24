package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.tab.TabHelper;
import com.leviathanstudio.mineide.util.Util;

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
        stage.getIcons().add(new Image(Util.IMG_DIR + "icon.png"));
        stage.setTitle(Gui.mineIdeInfo.getAppName() + " v" + Gui.mineIdeInfo.getAppVersion() + " " + "Forge "
                + Gui.mineIdeInfo.getForgeVersion());
        GuiMain.root = new StackPane();
        GuiMain.root.setId("mainFrame");
        VBox box = new VBox();
        GuiMain.root.getChildren().add(box);
        Scene scene = new Scene(GuiMain.root, Gui.width, Gui.height);
        scene.getStylesheets().add(MineIDE.class.getResource(Util.CSS_DIR + "style.css").toString());

        GuiActionBar.init(scene, box);
        GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

        stage.setScene(scene);
        stage.show();
    }
}
