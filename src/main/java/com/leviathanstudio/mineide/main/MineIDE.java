package com.leviathanstudio.mineide.main;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.GuiMain;
import com.leviathanstudio.mineide.ui.GuiSplash;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Example of displaying a splash page for a standalone JavaFX application
 */
public class MineIDE extends Application
{

    public static Stage      primaryStage;
    

    public static MineIDE instance;
    
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void init()
    {
        instance = this;
        
    }

    @Override
    public void start(final Stage initStage) throws Exception
    {
        initStage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));

        // init translation
        Translation.init();

        ObservableList<String> finishedPhaseTask = FXCollections.<String> observableArrayList();
        ObservableList<String> availablePhaseTask = FXCollections.observableArrayList("Pre-Initialization",
                "Initialization", "Post Initializtion", "Ending Initilization");
        ObservableList<String> finishedItemTask = FXCollections.<String> observableArrayList();
        ObservableList<String> availableItemTask = FXCollections.observableArrayList("Installing MineIDE",
                "Downloading Forge", "Installing Forge", "Building Workspace", "Initialize Java IDE", "Building UI",
                "Set Java Home");

        final Task<ObservableList<String>> loadingPhaseTask = new Task<ObservableList<String>>()
        {
            @Override
            protected ObservableList<String> call() throws InterruptedException
            {

                for (int i = 0; i < availablePhaseTask.size(); i++)
                {
                    Thread.sleep(100);
                    this.updateProgress(i + 1, availablePhaseTask.size());
                    String nextTask = availablePhaseTask.get(i);
                    finishedPhaseTask.add(nextTask);
                    this.updateMessage(nextTask);
                }
                Thread.sleep(100);

                return finishedPhaseTask;
            }
        };

        final Task<ObservableList<String>> loadingItemPhaseTask = new Task<ObservableList<String>>()
        {
            @Override
            protected ObservableList<String> call() throws InterruptedException
            {

                for (int i = 0; i < availableItemTask.size(); i++)
                {
                    Thread.sleep(100);
                    this.updateProgress(i + 1, availableItemTask.size());
                    String nextTask = availableItemTask.get(i);
                    finishedItemTask.add(nextTask);
                    this.updateMessage(nextTask);
                }
                Thread.sleep(100);
                this.updateMessage("Launching MineIDE");
                Thread.sleep(500);

                return finishedItemTask;
            }
        };

        GuiSplash splash = new GuiSplash();
        splash.init(initStage);
        splash.showSplash(initStage, loadingPhaseTask, loadingItemPhaseTask,
                () -> this.showMainStage(loadingPhaseTask.valueProperty()));
        new Thread(loadingPhaseTask).start();
        new Thread(loadingItemPhaseTask).start();
    }

    private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends)
    {
        MineIDE.primaryStage = new Stage(StageStyle.DECORATED);
        Gui main = new GuiMain();
        main.init(MineIDE.primaryStage);
        MineIDE.primaryStage.setMaximized(true);
        MineIDE.primaryStage.show();
    }  

    public interface InitCompletionHandler
    {
        void complete();
    }

    @Override
    public void stop()
    {
        Platform.exit();
    }
    
}