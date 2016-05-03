package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.actionBar.MenuEdit;
import com.leviathanstudio.mineide.ui.actionBar.MenuFile;
import com.leviathanstudio.mineide.ui.actionBar.MenuForge;
import com.leviathanstudio.mineide.ui.actionBar.MenuHelp;
import com.leviathanstudio.mineide.ui.actionBar.MenuOptions;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class GuiActionBar
{
    static MenuBar menuBar = new MenuBar();
    
    public static void init(Scene scene, VBox vbox)
    {
        menuBar.getMenus().addAll(new MenuFile().getMenuFile(), new MenuEdit().getMenuEdit(), new MenuForge().getMenuForge(), new MenuOptions().getMenuOptions(), new MenuHelp().getMenuHelp());
        vbox.getChildren().addAll(menuBar);
    }
}
