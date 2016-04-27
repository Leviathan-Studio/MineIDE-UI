package com.leviathanstudio.mineide.ui.frame.popup;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.utils.HtmlReader;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupCredits extends Gui
{
    
    public static void init()
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(MineIDE.primaryStage);
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        stage.setTitle("Credits");
        
        StackPane creditsVbox = new StackPane();
        
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        HtmlReader htmlReader = new HtmlReader();
        htmlReader.initReading(Utils.HTML_DIR + "credits.html");
        webEngine.loadContent(htmlReader.getOutputContent());
        
        creditsVbox.getChildren().add(browser);
        
        Scene scene = new Scene(creditsVbox, width / 1.5, height / 1.5);
        scene.setFill(Color.OLDLACE);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}