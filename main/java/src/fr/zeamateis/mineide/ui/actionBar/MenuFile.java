package fr.zeamateis.mineide.ui.actionBar;

import static fr.zeamateis.mineide.main.Translation.LANG;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

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
        
        MenuItem newProject = new MenuItem(LANG.getTranslation("menu.file.item.newProject"));
        newProject.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+P"));
        newProject.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        MenuItem saveAll = new MenuItem(LANG.getTranslation("menu.file.item.saveAll"));
        saveAll.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAll.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        MenuItem exit = new MenuItem(LANG.getTranslation("menu.file.item.exit"));
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                Platform.exit();
            }
        });
        
        menuFile.getItems().addAll(newProject, saveAll, exit);
    }
    
}