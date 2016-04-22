package fr.zeamateis.mineide.ui.actionBar;

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
        menuFile.getItems().addAll(newProject);
    }
    
}