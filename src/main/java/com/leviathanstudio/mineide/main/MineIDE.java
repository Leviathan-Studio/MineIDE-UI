package com.leviathanstudio.mineide.main;

import com.leviathanstudio.mineide.ui.GuiMain;
import com.leviathanstudio.mineide.ui.GuiScreen;
import com.leviathanstudio.mineide.util.Translation;
import com.leviathanstudio.mineide.util.Util;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MineIDE extends Application
{
    public static MineIDE instance;
    public static Stage   primaryStage;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void init()
    {
        MineIDE.instance = this;
        // Util.initDirectory();
        Translation.init();
        try
        {
            Thread.sleep(3000L);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MineIDE.primaryStage = new Stage(StageStyle.DECORATED);
        MineIDE.primaryStage.getIcons().add(new Image(Util.IMG_DIR + "icon.png"));
        MineIDE.primaryStage.setTitle(Util.APP_NAME);

        // stage.setTitle(Gui.mineIdeInfo.getAppName() + " v" +
        // Gui.mineIdeInfo.getAppVersion() + " " + "Forge "
        // + Gui.mineIdeInfo.getForgeVersion());
        GuiScreen main = new GuiMain();
        main.init();

        // MineIDE.primaryStage.setMaximized(true);

        main.show(MineIDE.primaryStage);
    }

    @Override
    public void stop()
    {
        Platform.exit();
    }

}
