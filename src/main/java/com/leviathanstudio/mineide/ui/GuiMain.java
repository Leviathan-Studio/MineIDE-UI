package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.menu.GuiMenuBar;
import com.leviathanstudio.mineide.ui.tool.GuiToolBar;

import javafx.scene.layout.StackPane;

public class GuiMain extends GuiScreen
{
    public GuiMain()
    {
        super(new StackPane());
        this.root.setId("mainFrame");
    }

    @Override
    public void init()
    {
        // stage.setOnCloseRequest((WindowEvent e) ->
        // {
        // TabHelper.closeAll();
        // });

        this.addPart(new GuiMenuBar());
        this.addPart(new GuiToolBar());

        // GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

    }
}