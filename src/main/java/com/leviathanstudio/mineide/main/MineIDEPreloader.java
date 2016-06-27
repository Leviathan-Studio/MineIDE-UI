package com.leviathanstudio.mineide.main;

import com.jfoenix.controls.JFXProgressBar;
import com.leviathanstudio.mineide.util.Util;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification.Type;
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

public class MineIDEPreloader extends Preloader
{
    private Stage          preloaderStage;

    private Pane           splashLayout;
    private JFXProgressBar loadProgressPhase, loadProgressItem;
    private Label          progressTextPhase, progressTextItem;

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        this.preloaderStage = primaryStage;

        ImageView splash = new ImageView(new Image(Util.IMG_DIR + "banner.png"));

        this.loadProgressPhase = new JFXProgressBar();
        this.loadProgressPhase.setPrefWidth(Util.SPLASH_WIDTH);
        this.loadProgressItem = new JFXProgressBar();
        this.loadProgressItem.setPrefWidth(Util.SPLASH_WIDTH);

        this.progressTextPhase = new Label();
        this.progressTextItem = new Label();

        this.splashLayout = new VBox();
        this.splashLayout.getChildren().addAll(splash, this.loadProgressPhase, this.loadProgressItem,
                this.progressTextPhase, this.progressTextItem);
        this.progressTextPhase.setAlignment(Pos.CENTER);
        this.progressTextItem.setAlignment(Pos.CENTER);

        this.splashLayout.setStyle("-fx-padding: 5; " + "-fx-background-color: gainsboro; " + "-fx-border-width:2; "
                + "-fx-border-color: " + "linear-gradient(" + "to bottom, " + "MediumSeaGreen, "
                + "derive(MediumSeaGreen, 50%)" + ");");
        this.splashLayout.setEffect(new DropShadow());

        Scene splashScene = new Scene(this.splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();

        primaryStage.setScene(splashScene);
        primaryStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - Util.SPLASH_WIDTH / 2);
        primaryStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - Util.SPLASH_HEIGHT / 2);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification)
    {
        if (stateChangeNotification.getType() == Type.BEFORE_START)
        {
            preloaderStage.hide();
        }
    }

}
