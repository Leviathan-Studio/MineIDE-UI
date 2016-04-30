package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.actionBar.*;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class GuiActionBar
{
    static MenuBar menuBar = new MenuBar();
    
    public static void init(Scene scene)
    {
        menuBar.getMenus().addAll(new MenuFile().getMenuFile(), new MenuEdit().getMenuEdit(), new MenuForge().getMenuForge(), new MenuOptions().getMenuOptions(), new MenuHelp().getMenuHelp());
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar);
    }
}
