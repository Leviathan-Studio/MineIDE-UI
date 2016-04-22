package fr.zeamateis.mineide.main;

import fr.zeamateis.mineide.ui.Gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MineIDE extends Application
{
    public static Stage primaryStage;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage stage)
    {
        primaryStage = stage;
        Gui.init(stage);
    }
    
}