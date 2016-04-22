package fr.zeamateis.mineide.ui.actionBar;

import fr.zeamateis.mineide.ui.frame.popup.GuiAbout;

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
        menuHelp = new Menu("Help");
        MenuItem credits = new MenuItem("About");
        credits.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                GuiAbout.init();
            }
        });
        menuHelp.getItems().addAll(credits);
    }
    
}