package com.leviathanstudio.mineide.ui.tab;

import java.io.PrintStream;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.utils.Console;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class TabConsole extends Pane
{

    public TabConsole()
    {
        TextArea ta = new TextArea();
        ta.prefWidth(MineIDE.primaryStage.getWidth());
        ta.prefHeight(MineIDE.primaryStage.getHeight());
        
        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);
        this.getChildren().add(ta);
    }
}
