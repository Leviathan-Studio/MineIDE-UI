package fr.zeamateis.mineide.ui.actionBar;

import fr.zeamateis.mineide.forge.ForgeWorkspace;
import fr.zeamateis.mineide.json.MineIDEInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuForge extends Menu
{
    Menu menuForge;
    
    public Menu getMenuForge()
    {
        return menuForge;
    }
    
    public MenuForge()
    {
        menuForge = new Menu("Forge");
        
        MenuItem installForge = new MenuItem("Install Forge (" + MineIDEInfo.getForgeVersion() + ")");
        installForge.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+F"));
        installForge.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                ForgeWorkspace.initialization();
            }
        });
        
        MenuItem forceForgeUpdate = new MenuItem("Force Forge Update");
        forceForgeUpdate.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+U"));
        forceForgeUpdate.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                ForgeWorkspace.forgeUpdate();
            }
        });
        
        MenuItem buildMod = new MenuItem("Build Mod");
        buildMod.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+B"));
        buildMod.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {}
        });
        
        menuForge.getItems().addAll(installForge, forceForgeUpdate, buildMod);
    }
    
}