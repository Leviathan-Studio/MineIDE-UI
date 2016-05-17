package com.leviathanstudio.mineide.ui.tab;

import java.io.PrintStream;

import com.leviathanstudio.mineide.utils.Console;

import javafx.scene.control.TextArea;

public class TabConsole extends TextArea
{

    public TabConsole()
    {
        Console console = new Console(this);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);
    }
}
