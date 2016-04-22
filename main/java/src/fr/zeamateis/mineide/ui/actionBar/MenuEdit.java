package fr.zeamateis.mineide.ui.actionBar;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuEdit extends Menu
{
    private Menu menuEdit;
    
    public Menu getMenuEdit()
    {
        return menuEdit;
    }
    
    public MenuEdit()
    {
        menuEdit = new Menu("Edit");
        
        // -----------
        MenuItem newClass = new MenuItem("New Class");
        newClass.setAccelerator(KeyCombination.keyCombination("Ctrl+J"));
        newClass.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        // -----------
        Menu newSpecifiedClass = new Menu("New Specified Class...");
        newSpecifiedClass.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        @SuppressWarnings("unchecked")
        final Entry<String, KeyCombination>[] keyCombination = new Entry[] {new SimpleEntry<String, KeyCombination>("Block", KeyCombination.keyCombination("Alt+1")), new SimpleEntry<String, KeyCombination>("Item", KeyCombination.keyCombination("Alt+2")), new SimpleEntry<String, KeyCombination>("Entity", KeyCombination.keyCombination("Alt+3")), new SimpleEntry<String, KeyCombination>("TileEntity", KeyCombination.keyCombination("Alt+4")), new SimpleEntry<String, KeyCombination>("Dimension", KeyCombination.keyCombination("Alt+5"))};
        
        for(Entry<String, KeyCombination> className : keyCombination)
        {
            MenuItem itemEffect = new MenuItem(className.getKey());
            itemEffect.setUserData(className.getKey());
            itemEffect.setAccelerator(className.getValue());
            newSpecifiedClass.getItems().add(itemEffect);
            itemEffect.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent t)
                {
                    System.out.println(itemEffect.getUserData());
                }
            });
        }
        
        menuEdit.getItems().addAll(newClass, newSpecifiedClass);
        
    }
}