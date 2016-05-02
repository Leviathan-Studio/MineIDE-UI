package com.leviathanstudio.mineide.main;

import com.leviathanstudio.mineide.java.generator.MainClassGenerator;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Example of displaying a splash page for a standalone JavaFX application
 */
public class MineIDE extends Application
{
    private Pane splashLayout;
    private ProgressBar loadProgressPhase, loadProgressItem;
    private Label progressTextPhase, progressTextItem;
    public static Stage primaryStage;
    private static final int SPLASH_WIDTH = 704;
    private static final int SPLASH_HEIGHT = 294;
    
    public static void main(String[] args) throws Exception
    {
        MainClassGenerator.setMainClassPackage("fr.zeamateis.test");
        MainClassGenerator.setMainClassName("testMainClass");
        MainClassGenerator.generateMainClass();
        launch(args);
    }
    
    @Override
    public void init()
    {
        ImageView splash = new ImageView(new Image(Utils.IMG_DIR + "banner.png"));
        
        loadProgressPhase = new ProgressBar();
        loadProgressPhase.setPrefWidth(SPLASH_WIDTH);
        loadProgressItem = new ProgressBar();
        loadProgressItem.setPrefWidth(SPLASH_WIDTH);
        
        progressTextPhase = new Label();
        progressTextItem = new Label();
        
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgressPhase, progressTextPhase, loadProgressItem, progressTextItem);
        
        progressTextPhase.setAlignment(Pos.CENTER);
        progressTextItem.setAlignment(Pos.CENTER);
        
        splashLayout.setStyle("-fx-padding: 5; " + "-fx-background-color: gainsboro; " + "-fx-border-width:2; " + "-fx-border-color: " + "linear-gradient(" + "to bottom, " + "MediumSeaGreen, " + "derive(MediumSeaGreen, 50%)" + ");");
        splashLayout.setEffect(new DropShadow());
    }
    
    @Override
    public void start(final Stage initStage) throws Exception
    {
        initStage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        
        // init translation
        Translation.init();
        
        ObservableList<String> finishedPhaseTask = FXCollections.<String>observableArrayList();
        ObservableList<String> availablePhaseTask = FXCollections.observableArrayList("Pre-Initialization", "Initialization", "Post Initializtion", "Ending Initilization");
        ObservableList<String> finishedItemTask = FXCollections.<String>observableArrayList();
        ObservableList<String> availableItemTask = FXCollections.observableArrayList("Installing MineIDE", "Downloading Forge", "Installing Forge", "Building Workspace", "Initialize Java IDE", "Building UI", "Set Java Home");
        
        final Task<ObservableList<String>> loadingPhaseTask = new Task<ObservableList<String>>()
        {
            @Override
            protected ObservableList<String> call() throws InterruptedException
            {
                
                for(int i = 0; i < availablePhaseTask.size(); i++)
                {
                    Thread.sleep(400);
                    updateProgress(i + 1, availablePhaseTask.size());
                    String nextTask = availablePhaseTask.get(i);
                    finishedPhaseTask.add(nextTask);
                    updateMessage(nextTask);
                }
                Thread.sleep(400);
                
                return finishedPhaseTask;
            }
        };
        
        final Task<ObservableList<String>> loadingItemPhaseTask = new Task<ObservableList<String>>()
        {
            @Override
            protected ObservableList<String> call() throws InterruptedException
            {
                
                for(int i = 0; i < availableItemTask.size(); i++)
                {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableItemTask.size());
                    String nextTask = availableItemTask.get(i);
                    finishedItemTask.add(nextTask);
                    updateMessage(nextTask);
                }
                Thread.sleep(400);
                updateMessage("Launching MineIDE");
                Thread.sleep(1000);
                
                return finishedItemTask;
            }
        };
        
        showSplash(initStage, loadingPhaseTask, loadingItemPhaseTask, () -> showMainStage(loadingPhaseTask.valueProperty()));
        new Thread(loadingPhaseTask).start();
        new Thread(loadingItemPhaseTask).start();
    }
    
    private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends)
    {
        primaryStage = new Stage(StageStyle.DECORATED);
        Gui.init(primaryStage);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    private void showSplash(final Stage initStage, Task<?> phaseTask, Task<?> itemTask, InitCompletionHandler initCompletionHandler)
    {
        progressTextPhase.textProperty().bind(phaseTask.messageProperty());
        progressTextItem.textProperty().bind(itemTask.messageProperty());
        
        loadProgressPhase.progressProperty().bind(phaseTask.progressProperty());
        phaseTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if(newState == Worker.State.SUCCEEDED)
            {
                loadProgressPhase.progressProperty().unbind();
                loadProgressPhase.setProgress(1);
            } // todo add code to gracefully handle other task states.
        });
        
        loadProgressItem.progressProperty().bind(itemTask.progressProperty());
        itemTask.stateProperty().addListener((observableValue, oldState, newState) -> {
            if(newState == Worker.State.SUCCEEDED)
            {
                loadProgressItem.progressProperty().unbind();
                loadProgressItem.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
                
                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });
        
        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
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