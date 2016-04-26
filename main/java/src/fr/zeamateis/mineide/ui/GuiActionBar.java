package fr.zeamateis.mineide.ui;

import fr.zeamateis.mineide.ui.actionBar.MenuEdit;
import fr.zeamateis.mineide.ui.actionBar.MenuFile;
import fr.zeamateis.mineide.ui.actionBar.MenuForge;
import fr.zeamateis.mineide.ui.actionBar.MenuHelp;
import fr.zeamateis.mineide.ui.actionBar.MenuOptions;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class GuiActionBar
{
    static MenuBar menuBar = new MenuBar();
    
    public static void init(Scene scene)
    {
        menuBar.getMenus().addAll(new MenuFile().getMenuFile(), new MenuForge().getMenuForge(), new MenuEdit().getMenuEdit(), new MenuOptions().getMenuOptions(), new MenuHelp().getMenuHelp());
        ((VBox)scene.getRoot()).getChildren().addAll(menuBar);
    }
}
