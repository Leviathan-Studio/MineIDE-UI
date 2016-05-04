package com.leviathanstudio.mineide.ui.frame.video;

import java.nio.ByteBuffer;

import com.sun.jna.Memory;

import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

/**
 * Example showing how to dynamically resize video.
 * <p>
 * Originally contributed by Vladislav Kisel, https://github.com/caprica/vlcj-javafx/pull/9, incorporated with minor changes.
 * <p>
 * The idea is to first determine the maximum size available (i.e. the screen size) and request that LibVLC send video frames in that size. We then scale *down* from the maximum size to fit the current window size, without any change in the native video buffer format.
 * <p>
 * So LibVLC will always be sending video frames in the maximum possible size.
 * <p>
 * This is a reasonable compromise.
 * <p>
 * For comparison, to achieve dynamic resizing by having LibVLC send video frames at a constantly changing size would require a constantly changing buffer format - and this would require you to get the current play-back position, stop the media player, play the media, set the new buffer format for the new size, and then restore the previous play-back position. Such an approach is clearly
 * problematic.
 */
public class TutorialTestFrame
{
    private static final String PATH_TO_VIDEO = "http://download.blender.org/peach/bigbuckbunny_movies/big_buck_bunny_480p_surround-fix.avi";
    private ImageView imageView;
    private DirectMediaPlayerComponent mediaPlayerComponent;
    private WritableImage writableImage;
    private Pane playerHolder;
    private WritablePixelFormat<ByteBuffer> pixelFormat;
    private FloatProperty videoSourceRatioProperty;
    
    public void init(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Video Tutorial - Simple Block");
        primaryStage.setHeight(480);
        primaryStage.setWidth(854);
        primaryStage.setResizable(true);
        
        final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>()
        {
            public void handle(final KeyEvent keyEvent)
            {
                if(keyEvent.getCode() == KeyCode.P)
                {
                    mediaPlayerComponent.getMediaPlayer().setPause(true);
                    keyEvent.consume();
                }
                else if(keyEvent.getCode() == KeyCode.S)
                {
                    mediaPlayerComponent.getMediaPlayer().setPause(false);
                    keyEvent.consume();
                }
            }
        };
        
        mediaPlayerComponent = new CanvasPlayerComponent();
        playerHolder = new Pane();
        videoSourceRatioProperty = new SimpleFloatProperty(0.4f);
        pixelFormat = PixelFormat.getByteBgraPreInstance();
        initializeImageView();
        Scene scene = new Scene(new BorderPane(playerHolder));
        primaryStage.setScene(scene);
        mediaPlayerComponent.getMediaPlayer().prepareMedia(PATH_TO_VIDEO);
        mediaPlayerComponent.getMediaPlayer().start();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
        primaryStage.show();
    }
    
    private void initializeImageView()
    {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        writableImage = new WritableImage((int)visualBounds.getWidth(), (int)visualBounds.getHeight());
        
        imageView = new ImageView(writableImage);
        playerHolder.getChildren().add(imageView);
        
        playerHolder.widthProperty().addListener((observable, oldValue, newValue) -> {
            fitImageViewSize(newValue.floatValue(), (float)playerHolder.getHeight());
        });
        
        playerHolder.heightProperty().addListener((observable, oldValue, newValue) -> {
            fitImageViewSize((float)playerHolder.getWidth(), newValue.floatValue());
        });
        
        videoSourceRatioProperty.addListener((observable, oldValue, newValue) -> {
            fitImageViewSize((float)playerHolder.getWidth(), (float)playerHolder.getHeight());
        });
    }
    
    private void fitImageViewSize(float width, float height)
    {
        Platform.runLater(() -> {
            float fitHeight = videoSourceRatioProperty.get() * width;
            if(fitHeight > height)
            {
                imageView.setFitHeight(height);
                double fitWidth = height / videoSourceRatioProperty.get();
                imageView.setFitWidth(fitWidth);
                imageView.setX((width - fitWidth) / 2);
                imageView.setY(0);
            }
            else
            {
                imageView.setFitWidth(width);
                imageView.setFitHeight(fitHeight);
                imageView.setY((height - fitHeight) / 2);
                imageView.setX(0);
            }
        });
    }
    
    private class CanvasPlayerComponent extends DirectMediaPlayerComponent
    {
        
        public CanvasPlayerComponent()
        {
            super(new CanvasBufferFormatCallback());
        }
        
        PixelWriter pixelWriter = null;
        
        private PixelWriter getPW()
        {
            if(pixelWriter == null)
            {
                pixelWriter = writableImage.getPixelWriter();
            }
            return pixelWriter;
        }
        
        @Override
        public void display(DirectMediaPlayer mediaPlayer, Memory[] nativeBuffers, BufferFormat bufferFormat)
        {
            if(writableImage == null)
            {
                return;
            }
            Platform.runLater(() -> {
                Memory nativeBuffer = mediaPlayer.lock()[0];
                try
                {
                    ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0, nativeBuffer.size());
                    getPW().setPixels(0, 0, bufferFormat.getWidth(), bufferFormat.getHeight(), pixelFormat, byteBuffer, bufferFormat.getPitches()[0]);
                }
                finally
                {
                    mediaPlayer.unlock();
                }
            });
        }
    }
    
    private class CanvasBufferFormatCallback implements BufferFormatCallback
    {
        @Override
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight)
        {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            Platform.runLater(() -> videoSourceRatioProperty.set((float)sourceHeight / (float)sourceWidth));
            return new RV32BufferFormat((int)visualBounds.getWidth(), (int)visualBounds.getHeight());
        }
    }
    
    public TutorialTestFrame(Stage stage)
    {
        try
        {
            VlcManager.loadNatives();
            this.init(stage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
