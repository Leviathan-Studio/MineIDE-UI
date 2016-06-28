package com.leviathanstudio.mineide.ui.tool;

import com.leviathanstudio.mineide.ui.GuiPart;
import com.leviathanstudio.mineide.ui.control.IconLabel;
import com.leviathanstudio.mineide.util.Util;

import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
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
        IconLabel il = new IconLabel(new ImageView(Util.getResouce("/mineIDE/img/addIcon.png")), "test");

        ToolBar toolBar = new ToolBar();

        toolBar.getItems().add(il);
        this.addElement(toolBar);
    }

}
