package com.leviathanstudio.mineide.ui;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public abstract class Gui
{
    public abstract void init(Stage stage);

    public static void setToolTip(Node node, String toolTipText)
    {
        Tooltip toolTip = new Tooltip();
        toolTip.setText(toolTipText);
        Tooltip.install(node, toolTip);
    }
}
