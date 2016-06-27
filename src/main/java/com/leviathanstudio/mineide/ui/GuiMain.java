package com.leviathanstudio.mineide.ui;

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

        // GuiJavaEditor.init(scene, box);
        // GuiConsole.init(scene);

    }
}