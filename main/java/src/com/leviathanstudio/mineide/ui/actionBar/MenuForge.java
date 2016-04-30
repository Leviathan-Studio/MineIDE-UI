package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import java.io.IOException;

import com.leviathanstudio.mineide.forge.ForgeHelper;
import com.leviathanstudio.mineide.forge.ForgeWorkspace;
import com.leviathanstudio.mineide.ui.component.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

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
        
        MenuItem installForge = new MenuItemTranslate("menu.forge.item.install", "Ctrl+Alt+F", (ActionEvent t) -> {
            ForgeWorkspace.installWorkspace();
        });
        MenuItem forceForgeUpdate = new MenuItemTranslate("menu.forge.item.forceUpdate", "Ctrl+Alt+U", (ActionEvent t) -> {
            ForgeWorkspace.forceUpdate();
        });
        
        MenuItem buildMod = new MenuItemTranslate("menu.forge.item.build", "Ctrl+Alt+B", (ActionEvent t) -> {
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