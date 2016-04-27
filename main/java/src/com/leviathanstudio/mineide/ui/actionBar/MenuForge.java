package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import java.io.IOException;

import com.leviathanstudio.mineide.forge.ForgeHelper;
import com.leviathanstudio.mineide.forge.ForgeWorkspace;
import com.leviathanstudio.mineide.json.MineIDEConfig;

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
        installForge.setOnAction((ActionEvent t) -> {
            ForgeWorkspace.installWorkspace();
        });
        
        MenuItem forceForgeUpdate = new MenuItem(LANG.getTranslation("menu.forge.item.forceUpdate"));
        forceForgeUpdate.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+U"));
        forceForgeUpdate.setOnAction((ActionEvent t) -> {
            ForgeWorkspace.forceUpdate();
        });
        
        MenuItem buildMod = new MenuItem(LANG.getTranslation("menu.forge.item.build"));
        buildMod.setAccelerator(KeyCombination.keyCombination("Ctrl+Alt+B"));
        buildMod.setOnAction((ActionEvent t) -> {
            try
            {
                ForgeHelper.compileToJar();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        });
        
        menuForge.getItems().addAll(installForge, forceForgeUpdate, buildMod);
    }
    
}