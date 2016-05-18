package com.leviathanstudio.mineide.ui.tab;

import java.io.FileNotFoundException;

import com.leviathanstudio.mineide.utils.Console;

import javafx.scene.control.TextArea;

public class TabConsole extends TextArea
{
    public TabConsole()
    {
        try
        {
            Console console = new Console(this);
            System.setOut(console);
            System.setErr(console);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
