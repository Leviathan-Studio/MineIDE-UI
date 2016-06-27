package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.control.MenuItemIcon;
import com.leviathanstudio.mineide.ui.menu.MenuCategory;
import com.leviathanstudio.mineide.ui.menu.MenuManagement;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiActionBar extends Gui
{

    @Override
    public void init(Stage stage)
    {
        VBox box = new VBox();
        MenuBar menuBar = new MenuBar();
        GuiMain.root.getChildren().add(box);
        MenuManagement menuManagement = new MenuManagement(menuBar);

        MenuCategory catFileProject = new MenuCategory("project");
        MenuCategory catFileSave = new MenuCategory("save");
        MenuCategory catFileClose = new MenuCategory("close");
        MenuCategory catFileExit = new MenuCategory("exit");

        catFileProject.add(new MenuItemIcon("menu.file.item.newProject", "/mineIDE/img/addIcon.png", "Ctrl+Alt+P", (ActionEvent t) ->{}));
        catFileProject.add(new MenuItemIcon("menu.file.item.openProject", "", "Ctrl+Alt+O", (ActionEvent t) ->{}));
        catFileProject.add(new MenuItemIcon("menu.file.item.closeProject", "", "Ctrl+Alt+W", (ActionEvent t) ->{}));

        catFileSave.add(new MenuItemIcon("menu.file.item.save", "", "Ctrl+S", (ActionEvent t) ->{}));
        catFileSave.add(new MenuItemIcon("menu.file.item.saveAll", "", "Ctrl+Shift+S", (ActionEvent t) ->{}));
        
        catFileClose.add(new MenuItemIcon("menu.file.item.close", "", "Ctrl+W", (ActionEvent t) ->{}));
        catFileClose.add(new MenuItemIcon("menu.file.item.closeAll", "", "Ctrl+Shift+W", (ActionEvent t) ->{}));
        
        catFileExit.add(new MenuItemIcon("menu.file.item.exit", "", "Ctrl+Q", (ActionEvent t) ->{}));

        menuManagement.addMenu("menu.file", catFileProject, catFileSave, catFileClose, catFileExit);
//        menuManagement.addMenu("menu.edit", menuCats);
//        menuManagement.addMenu("menu.forge", menuCats);
//        menuManagement.addMenu("menu.minecraft", menuCats);
//        menuManagement.addMenu("menu.window", menuCats);
//        menuManagement.addMenu("menu.help", menuCats);
        box.getChildren().add(menuBar);

    }

}
