package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.ui.component.SpecificClasses;
import com.leviathanstudio.mineide.ui.controls.MenuItemIcon;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuEdit extends Menu
{
    private Menu menuEdit;
    
    public Menu getMenuEdit()
    {
        return menuEdit;
    }
    
    public MenuEdit()
    {
        SpecificClasses.initSubClasses();
        
        menuEdit = new Menu(LANG.getTranslation("menu.edit"));
        
        MenuItem undo = new MenuItemIcon("menu.edit.item.undo", "", "Ctrl+Z", (ActionEvent t) -> {});
        MenuItem redo = new MenuItemIcon("menu.edit.item.redo", "", "Ctrl+Y", (ActionEvent t) -> {});
        
        MenuItem cut = new MenuItemIcon("menu.edit.item.cut", "", "Ctrl+X", (ActionEvent t) -> {});
        MenuItem copy = new MenuItemIcon("menu.edit.item.copy", "", "Ctrl+C", (ActionEvent t) -> {});
        MenuItem paste = new MenuItemIcon("menu.edit.item.paste", "", "Ctrl+V", (ActionEvent t) -> {});
        MenuItem delete = new MenuItemIcon("menu.edit.item.delete", "", "Del", (ActionEvent t) -> {});
        MenuItem selectAll = new MenuItemIcon("menu.edit.item.selectAll", "", "Ctrl+A", (ActionEvent t) -> {});
        
        
        menuEdit.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete, selectAll);
    }
}