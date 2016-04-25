package fr.zeamateis.mineide.ui.actionBar;

import static fr.zeamateis.mineide.main.Translation.LANG;

import java.io.IOException;

import fr.zeamateis.mineide.forge.ForgeHelper;
import fr.zeamateis.mineide.forge.ForgeWorkspace;
import fr.zeamateis.mineide.json.MineIDEConfig;

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
        menuForge = new Menu(LANG.getTranslation("menu.forge"));
        
        MenuItem installForge = new MenuItem(LANG.getTranslation("menu.forge.item.install") + " (" + MineIDEConfig.getForgeVersion() + ")");
        installForge.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+F"));
        installForge.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                ForgeWorkspace.initialization();
            }
        });
        
        MenuItem forceForgeUpdate = new MenuItem(LANG.getTranslation("menu.forge.item.forceUpdate"));
        forceForgeUpdate.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+U"));
        forceForgeUpdate.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                ForgeWorkspace.forgeUpdate();
            }
        });
        
        MenuItem buildMod = new MenuItem(LANG.getTranslation("menu.forge.item.build"));
        buildMod.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+B"));
        buildMod.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                try
                {
                    ForgeHelper.compileToJar();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        menuForge.getItems().addAll(installForge, forceForgeUpdate, buildMod);
    }
    
}