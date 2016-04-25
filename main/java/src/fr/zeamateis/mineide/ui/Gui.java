package fr.zeamateis.mineide.ui;

import fr.zeamateis.mineide.json.MineIDEConfig;
import fr.zeamateis.mineide.main.MineIDE;
import fr.zeamateis.mineide.utils.Utils;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui
{
    public static int width = 854, height = 480;
    static MineIDEConfig mineIdeInfo = new MineIDEConfig();
    public static MineIDE mineIde;
    
    public static void init(Stage stage)
    {
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        stage.setTitle(mineIdeInfo.getAppName() + " v" + mineIdeInfo.getAppVersion() + " " + "Forge " + mineIdeInfo.getForgeVersion());
        VBox root = new VBox();
        root.setId("mainFrame");
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(MineIDE.class.getResource(Utils.CSS_DIR + "style.css").toString());
        
        GuiActionBar.init(scene);
        GuiJavaEditor.init(scene);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setToolTip(String toolTipText)
    {
        new Tooltip(toolTipText);
    }
    
    public static void setToolTip(Node node, String toolTipText)
    {
        Tooltip toolTip = new Tooltip();
        toolTip.setText(toolTipText);
        Tooltip.install(node, toolTip);
    }
}