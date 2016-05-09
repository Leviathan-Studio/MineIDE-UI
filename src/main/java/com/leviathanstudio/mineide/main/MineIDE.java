package com.leviathanstudio.mineide.main;

import com.jfoenix.controls.JFXProgressBar;
import com.leviathanstudio.mineide.ui.GuiJavaEditor;
import com.leviathanstudio.mineide.ui.GuiMain;
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
    private Pane             splashLayout;
    private JFXProgressBar   loadProgressPhase, loadProgressItem;
    private Label            progressTextPhase, progressTextItem;
    public static Stage      primaryStage;
    private static final int SPLASH_WIDTH  = 704;
    private static final int SPLASH_HEIGHT = 294;

    public static MineIDE instance;
    
    public static void main(String[] args) throws Exception
    {
        launch(args);
    }

    @Override
    public void init()
    {
        instance = this;
        ImageView splash = new ImageView(new Image(Utils.IMG_DIR + "banner.png"));

        this.loadProgressPhase = new JFXProgressBar();
        this.loadProgressPhase.setPrefWidth(MineIDE.SPLASH_WIDTH);
        this.loadProgressItem = new JFXProgressBar();
        this.loadProgressItem.setPrefWidth(MineIDE.SPLASH_WIDTH);

        this.progressTextPhase = new Label();
        this.progressTextItem = new Label();

        this.splashLayout = new VBox();
        this.splashLayout.getChildren().addAll(splash, this.loadProgressPhase, this.progressTextPhase,
                this.loadProgressItem, this.progressTextItem);

        this.progressTextPhase.setAlignment(Pos.CENTER);
        this.progressTextItem.setAlignment(Pos.CENTER);

        this.splashLayout.setStyle("-fx-padding: 5; " + "-fx-background-color: gainsboro; " + "-fx-border-width:2; "
                + "-fx-border-color: " + "linear-gradient(" + "to bottom, " + "MediumSeaGreen, "
                + "derive(MediumSeaGreen, 50%)" + ");");
        this.splashLayout.setEffect(new DropShadow());
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

        this.showSplash(initStage, loadingPhaseTask, loadingItemPhaseTask,
                () -> this.showMainStage(loadingPhaseTask.valueProperty()));
        new Thread(loadingPhaseTask).start();
        new Thread(loadingItemPhaseTask).start();
    }

    private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends)
    {
        MineIDE.primaryStage = new Stage(StageStyle.DECORATED);
        GuiMain.init(MineIDE.primaryStage);
        MineIDE.primaryStage.setMaximized(true);
        MineIDE.primaryStage.show();
    }

    private void showSplash(final Stage initStage, Task<?> phaseTask, Task<?> itemTask,
            InitCompletionHandler initCompletionHandler)
    {
        this.progressTextPhase.textProperty().bind(phaseTask.messageProperty());
        this.progressTextItem.textProperty().bind(itemTask.messageProperty());

        this.loadProgressPhase.progressProperty().bind(phaseTask.progressProperty());
        phaseTask.stateProperty().addListener((observableValue, oldState, newState) ->
        {
            if (newState == Worker.State.SUCCEEDED)
            {
                this.loadProgressPhase.progressProperty().unbind();
                this.loadProgressPhase.setProgress(1);
            } // todo add code to gracefully handle other task states.
        });

        this.loadProgressItem.progressProperty().bind(itemTask.progressProperty());
        itemTask.stateProperty().addListener((observableValue, oldState, newState) ->
        {
            if (newState == Worker.State.SUCCEEDED)
            {
                this.loadProgressItem.progressProperty().unbind();
                this.loadProgressItem.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), this.splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(this.splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - MineIDE.SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - MineIDE.SPLASH_HEIGHT / 2);
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