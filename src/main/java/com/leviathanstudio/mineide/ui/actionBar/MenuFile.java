package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.ui.GuiJavaEditor;
import com.leviathanstudio.mineide.ui.controls.MenuItemIcon;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;
import com.leviathanstudio.mineide.ui.frame.minecraft.PopupCreateProject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuFile extends Menu
{
    private Menu menuFile;
    
    public Menu getMenuFile()
    {
        return menuFile;
    }
    
    public MenuFile()
    {
        menuFile = new Menu(LANG.getTranslation("menu.file"));
        
        MenuItem newProject = new MenuItemIcon("menu.file.item.newProject", "/mineIDE/img/addIcon.png", "Ctrl+Alt+P", (ActionEvent t) -> {
            PopupCreateProject.init();
        });
        MenuItem openProject = new MenuItemIcon("menu.file.item.openProject", "", "Ctrl+Alt+O", (ActionEvent t) -> {
            GuiJavaEditor.tabBar.addTab("Test_" + (int)(Math.random() * 100) + ".java", "Test_" + (int)(Math.random() * 100), new CodeEditor(""));
        });
        MenuItem closeProject = new MenuItemIcon("menu.file.item.closeProject", "", "Ctrl+Alt+W", (ActionEvent t) -> {});
        
        MenuItem save = new MenuItemIcon("menu.file.item.save", "", "Ctrl+S", (ActionEvent t) -> {});
        MenuItem saveAll = new MenuItemIcon("menu.file.item.save", "", "Ctrl+Shift+S", (ActionEvent t) -> {});
        
        MenuItem close = new MenuItemIcon("menu.file.item.close", "", "Ctrl+W", (ActionEvent t) -> {
            GuiJavaEditor.tabBar.closeCurrentTab();
        });
        MenuItem closeAll = new MenuItemIcon("menu.file.item.closeAll", "", "Ctrl+Shift+W", (ActionEvent t) -> {
            // TODO Close all Tab in all windows
            GuiJavaEditor.tabBar.closeAll();
        });
        
        MenuItem exit = new MenuItemIcon("menu.file.item.exit", "", "Ctrl+Q", (ActionEvent t) -> {
            Platform.exit();
            
        });
        
        menuFile.getItems().addAll(newProject, openProject, closeProject, new SeparatorMenuItem(), save, saveAll, new SeparatorMenuItem(), close, closeAll, new SeparatorMenuItem(), exit);
    }
}