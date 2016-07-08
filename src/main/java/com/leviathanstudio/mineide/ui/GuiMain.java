package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.menu.GuiMenuBar;
import com.leviathanstudio.mineide.ui.tool.GuiToolBar;

import javafx.scene.layout.StackPane;

public class GuiMain extends GuiScreen
{
    public static GuiMain INSTANCE;

    public GuiMain()
    {
        super(new StackPane());
        this.root.setId("mainFrame");
        INSTANCE = this;
    }

    @Override
    public void init()
    {
        // stage.setOnCloseRequest((WindowEvent e) ->
        // {
        // TabHelper.closeAll();
        // });

        this.addPart(new GuiMenuBar(this.root));
        this.addPart(new GuiToolBar(this.root));

        // GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

    }
}