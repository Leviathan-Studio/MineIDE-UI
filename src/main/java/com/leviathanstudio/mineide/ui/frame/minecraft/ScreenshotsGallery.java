package com.leviathanstudio.mineide.ui.frame.minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.utils.Util;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ScreenshotsGallery
{
    Stage            stage;
    static ImageView imageView = null;

    public ScreenshotsGallery() throws Exception
    {
        this.stage = MineIDE.primaryStage;

        this.stage = new Stage(StageStyle.DECORATED);
        this.stage.initStyle(StageStyle.TRANSPARENT);
        ScrollPane root = new ScrollPane();
        TilePane tile = new TilePane();
        root.setStyle("-fx-padding: 2.5; " + "-fx-background-color: gainsboro; " + "-fx-border-width:2; "
                + "-fx-border-color: " + "linear-gradient(" + "to bottom, " + "CornflowerBlue, "
                + "derive(MediumSeaGreen, 50%)" + ");");
        root.setEffect(new DropShadow());
        tile.setPadding(new Insets(15, 15, 15, 15));
        tile.setHgap(15);

        String path = Util.FORGE_DIR + "/run/screenshots/";

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles)
        {
            ImageView imageView;
            imageView = this.createImageView(file);
            tile.getChildren().addAll(imageView);
        }

        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical
                                                                  // scroll bar
        root.setFitToWidth(true);
        root.setContent(tile);

        this.stage.setTitle("Minecraft Screenshots Gallery");
        this.stage.getIcons().add(new Image(Util.IMG_DIR + "icon.png"));
        this.stage.toFront();
        this.stage.setWidth(854);
        this.stage.setHeight(480);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED && keyEvent.getCode() == KeyCode.ESCAPE)
                    Platform.exit();
            }
        });

        this.stage.setScene(scene);
        this.stage.show();
    }

    private ImageView createImageView(final File imageFile)
    {
        try
        {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true, true);
            ScreenshotsGallery.imageView = new ImageView(image);
            ScreenshotsGallery.imageView.setFitWidth(150);
            ScreenshotsGallery.imageView.setOnMouseClicked(new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {

                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY))
                        if (mouseEvent.getClickCount() == 1)
                            try
                            {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setResizable(false);
                                newStage.setWidth(image.getWidth());
                                newStage.setHeight(image.getHeight() + 30);
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane, Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                }
            });
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return ScreenshotsGallery.imageView;
    }
}