package fr.zeamateis.mineide.ui.actionBar;

import fr.zeamateis.mineide.main.MineIDETrayIcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuFile extends Menu
{
    Menu menuFile;
    
    public Menu getMenuFile()
    {
        return menuFile;
    }
    
    public MenuFile()
    {
        menuFile = new Menu("File");
        
        MenuItem newProject = new MenuItem("New Project");
        newProject.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+P"));
        newProject.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        MenuItem saveAll = new MenuItem("Save All");
        saveAll.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAll.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        MenuItem exit = new MenuItem("Exit");
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                MineIDETrayIcon.killTrayIcon();
            }
        });
        
        menuFile.getItems().addAll(newProject, saveAll, exit);
    }
    
}