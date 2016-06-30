package com.leviathanstudio.mineide.ui.tool;

import com.leviathanstudio.mineide.ui.GuiPart;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;

public class GuiToolBar extends GuiPart
{

    public GuiToolBar()
    {
        super(new VBox());
        this.root.setTranslateY(25);
    }

    @Override
    public void init()
    {
        ToolBar toolBar = new ToolBar();
        ToolManagement toolManagement = new ToolManagement(toolBar);

        toolManagement.addButton("/mineIDE/img/addIcon.png");
        toolManagement.addButton("/mineIDE/img/color.png");

        this.addElement(toolBar);
    }

}
