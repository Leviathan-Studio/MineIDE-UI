package com.leviathanstudio.mineide.main;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MineIDE extends Application
{
    public static Stage primaryStage;
    
    public static void main(String[] args)
    {
        Translation.init();
        // System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_65\\");
        launch(args);
    }
    
    @Override
    public void start(Stage stage)
    {
        primaryStage = stage;
        Utils.checkDir(Utils.CONFIG);
        Utils.checkDir(Utils.PROJECT);

        Gui.init(stage);
        //
        // TemplateReader tmpltReader = new TemplateReader();
        // tmpltReader.initReading("java", "main/ModClass");
        // System.out.println(tmpltReader.getOutputContent());
        //
        // JavaClassWriter javaClassWriter = new JavaClassWriter();
        // javaClassWriter.initWritting("test", tmpltReader.getOutputContent());
        //
    }
    
    @Override
    public void stop()
    {
        Platform.exit();
    }
    
}