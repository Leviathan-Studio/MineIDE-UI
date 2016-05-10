package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.json.MineIDEConfig;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class Gui
{
    public static int width = 854, height = 480;
    public static MineIDEConfig mineIdeInfo = new MineIDEConfig();
    
    public void init(Stage stage)
    {}
    
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