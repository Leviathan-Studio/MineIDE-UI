package com.leviathanstudio.mineide.main;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.GuiMain;
import com.leviathanstudio.mineide.util.Translation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MineIDE extends Application
{
    public static MineIDE instance;
    public static Stage   primaryStage;

    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void init()
    {
        MineIDE.instance = this;
        // Util.initDirectory();
        Translation.init();

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // TODO Auto-generated method stub
        showMainStage();
    }

    private void showMainStage()
    {
        MineIDE.primaryStage = new Stage(StageStyle.DECORATED);
        Gui main = new GuiMain();
        main.init(MineIDE.primaryStage);
        MineIDE.primaryStage.setMaximized(true);
        MineIDE.primaryStage.show();
    }

    @Override
    public void stop()
    {
        Platform.exit();
    }

}
