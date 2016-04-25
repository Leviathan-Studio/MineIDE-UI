package fr.zeamateis.mineide.ui.actionBar;

import static fr.zeamateis.mineide.main.Translation.LANG;

import fr.zeamateis.mineide.ui.frame.popup.PopupCredits;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        MenuItem credits = new MenuItem(LANG.getTranslation("menu.help.item.about"));
        credits.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                PopupCredits.init();
            }
        });
        menuHelp.getItems().addAll(credits);
    }
    
}