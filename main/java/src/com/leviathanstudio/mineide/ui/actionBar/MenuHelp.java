package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.ui.component.MenuItemTranslate;
import com.leviathanstudio.mineide.ui.frame.popup.PopupCredits;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuHelp extends Menu
{
    Menu menuHelp;
    
    public Menu getMenuHelp()
    {
        return menuHelp;
    }
    
    public MenuHelp()
    {
        menuHelp = new Menu(LANG.getTranslation("menu.help"));
        
        MenuItem credits = new MenuItemTranslate("menu.help.item.about", "F1", (ActionEvent t) -> {
            PopupCredits.init();
        });
        
        menuHelp.getItems().addAll(credits);
    }
}