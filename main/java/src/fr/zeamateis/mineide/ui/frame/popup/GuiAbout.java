package fr.zeamateis.mineide.ui.frame.popup;

import fr.zeamateis.mineide.json.MineIDEInfo;
import fr.zeamateis.mineide.main.MineIDE;
import fr.zeamateis.mineide.ui.Gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuiAbout extends Gui
{
    
    public static void init()
    {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(MineIDE.primaryStage);
        stage.getIcons().add(new Image("/img/icon.png"));
        stage.setTitle("Credits");
        
        VBox creditsVbox = new VBox(20);
        Text creditsText = new Text();
        creditsText.setFont(Font.font("Consolas", FontWeight.BOLD, 12));
        creditsText.setText(MineIDEInfo.getCredits());
        creditsVbox.getChildren().add(creditsText);
        
        Scene scene = new Scene(creditsVbox, width / 2, height / 2);
        scene.setFill(Color.OLDLACE);
        
        stage.setScene(scene);
        stage.show();
    }
}