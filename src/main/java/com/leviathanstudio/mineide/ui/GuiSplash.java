package com.leviathanstudio.mineide.ui;

import com.jfoenix.controls.JFXProgressBar;
import com.leviathanstudio.mineide.main.MineIDE.InitCompletionHandler;
import com.leviathanstudio.mineide.util.Util;

import javafx.animation.FadeTransition;
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

public class GuiSplash extends Gui
{
    private Pane             splashLayout;
    private JFXProgressBar   loadProgressPhase, loadProgressItem;
    private Label            progressTextPhase, progressTextItem;

    private static final int SPLASH_WIDTH  = 704;
    private static final int SPLASH_HEIGHT = 294;

    @Override
    public void init(Stage stage)
    {
        ImageView splash = new ImageView(new Image(Util.IMG_DIR + "banner.png"));

        this.loadProgressPhase = new JFXProgressBar();
        this.loadProgressPhase.setPrefWidth(GuiSplash.SPLASH_WIDTH);
        this.loadProgressItem = new JFXProgressBar();
        this.loadProgressItem.setPrefWidth(GuiSplash.SPLASH_WIDTH);

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

    public void showSplash(final Stage initStage, Task<?> phaseTask, Task<?> itemTask,
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
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - GuiSplash.SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - GuiSplash.SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }
}
