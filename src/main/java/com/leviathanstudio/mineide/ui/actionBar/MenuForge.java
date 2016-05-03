package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import java.io.IOException;

import com.leviathanstudio.mineide.forge.ForgeHelper;
import com.leviathanstudio.mineide.forge.ForgeWorkspace;
import com.leviathanstudio.mineide.ui.component.SpecificClasses;
import com.leviathanstudio.mineide.ui.controls.MenuItemIcon;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

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
        
        MenuItem installForge = new MenuItemIcon("menu.forge.item.install", "", "Ctrl+Alt+F", (ActionEvent t) -> {
            ForgeWorkspace.installWorkspace();
        });
        MenuItem forceForgeUpdate = new MenuItemIcon("menu.forge.item.forceUpdate", "", "Ctrl+Alt+U", (ActionEvent t) -> {
            ForgeWorkspace.forceUpdate();
        });
        
        MenuItem buildMod = new MenuItemIcon("menu.forge.item.build", "", "Ctrl+Alt+B", (ActionEvent t) -> {
            try
            {
                ForgeHelper.compileToJar();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        });
        
// -----------
        
        MenuItem newClass = new MenuItemTranslate("menu.edit.item.newClass", "Ctrl+J", (ActionEvent t) -> {});
        
        // -----------
        Menu newSpecifiedClass = new Menu(LANG.getTranslation("menu.edit.item.newSpecificClass"));
        newSpecifiedClass.setOnAction((ActionEvent t) -> {});
        
        final String[] specificClass = new String[] {SpecificClasses.getBlock(), SpecificClasses.getItem(), SpecificClasses.getEntity(), SpecificClasses.getTileEntity(), SpecificClasses.getDimension()};
        
        for(String className : specificClass)
        {
            Menu classesMenu = new Menu(className.toString());
            classesMenu.setUserData(className.toString());
            newSpecifiedClass.getItems().add(classesMenu);
            classesMenu.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent t)
                {
                    System.out.println(classesMenu.getUserData());
                }
            });
            
            if(className.toString().equals(SpecificClasses.getBlock()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicBlock(), SpecificClasses.getOreBlock());
            else if(className.toString().equals(SpecificClasses.getItem()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicItem(), SpecificClasses.getArmorItem(), SpecificClasses.getToolItem(), SpecificClasses.getWeaponItem());
            else if(className.toString().equals(SpecificClasses.getEntity()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicEntity(), SpecificClasses.getAnimalEntity(), SpecificClasses.getMonsterEntity(), SpecificClasses.getWaterEntity(), SpecificClasses.getMountEntity(), SpecificClasses.getBossEntity());
            else if(className.toString().equals(SpecificClasses.getTileEntity()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicTileEntity(), SpecificClasses.getTileEntityTesr());
            else if(className.toString().equals(SpecificClasses.getDimension()))
                classesMenu.getItems().addAll(SpecificClasses.getDimOverworld(), SpecificClasses.getDimNether(), SpecificClasses.getDimEnd());
        }
        
        menuForge.getItems().addAll(installForge, forceForgeUpdate, buildMod, new SeparatorMenuItem(), newClass, newSpecifiedClass);
    }
}