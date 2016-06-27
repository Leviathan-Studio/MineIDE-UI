package com.leviathanstudio.mineide.main;

import com.sun.javafx.application.LauncherImpl;

@SuppressWarnings("restriction")
public class MineIDEStart
{
    public static void main(String[] args)
    {
        LauncherImpl.launchApplication(MineIDE.class, MineIDEPreloader.class, args);
    }

}
