package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.ui.component.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuOptions extends Menu
{
    Menu menuOptions;
    
    public Menu getMenuOptions()
    {
        return menuOptions;
    }
    
    public MenuOptions()
    {
        menuOptions = new Menu(LANG.getTranslation("menu.options"));
        
        MenuItem menuPreferences = new MenuItemTranslate("menu.options.item.preferences", (ActionEvent t) -> {});
        
        menuOptions.getItems().addAll(menuPreferences);
    }
}