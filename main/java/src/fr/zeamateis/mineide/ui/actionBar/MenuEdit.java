package fr.zeamateis.mineide.ui.actionBar;

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
        final String[] specificClass = new String[] {new String("Block"), new String("Item"), new String("Entity"), new String("TileEntity"), new String("Dimension")};
        
        for(String className : specificClass)
        {
            Menu classesMenu = new Menu(className.toString());
            classesMenu.setUserData(className.toString());
            newSpecifiedClass.getItems().add(classesMenu);
            classesMenu.setOnAction(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent t)
                {
                    System.out.println(classesMenu.getUserData());
                }
            });
            
            switch(className.toString())
            {
                case "Block":
                    classesMenu.getItems().addAll(new MenuItem("Basic Block"), new MenuItem("Ore Block"));
                    break;
                case "Item":
                    classesMenu.getItems().addAll(new MenuItem("Basic Item"), new MenuItem("Armor Item"), new MenuItem("Tool Item"), new MenuItem("Weapon Item"));
                    break;
                case "Entity":
                    classesMenu.getItems().addAll(new MenuItem("Basic Entity"), new MenuItem("Passive Entity"), new MenuItem("Monster Entity"), new MenuItem("Boss Entity"));
                    break;
                case "TileEntity":
                    classesMenu.getItems().addAll(new MenuItem("Basic TileEntity"), new MenuItem("TileEntity with TESR"));
                    break;
                case "Dimension":
                    classesMenu.getItems().addAll(new MenuItem("Overworld Like Dimension"), new MenuItem("Nether Like Dimension"), new MenuItem("End Like Dimension"));
                    break;
                default:
                    break;
            }
        }
        
        menuEdit.getItems().addAll(newClass, newSpecifiedClass);
        
    }
}