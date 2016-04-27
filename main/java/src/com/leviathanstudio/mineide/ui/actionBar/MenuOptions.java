package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.ui.frame.popup.PopupCredits;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        MenuItem menuPreferences = new MenuItem(LANG.getTranslation("menu.options.item.preferences"));
        menuPreferences.setOnAction((ActionEvent t) -> {});
        
        menuOptions.getItems().addAll(menuPreferences);
    }
    
}