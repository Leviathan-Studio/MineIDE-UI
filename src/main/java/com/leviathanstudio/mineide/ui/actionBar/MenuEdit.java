package com.leviathanstudio.mineide.ui.actionBar;

import static com.leviathanstudio.mineide.main.Translation.*;

import com.leviathanstudio.mineide.ui.component.MenuItemTranslate;
import com.leviathanstudio.mineide.ui.component.SpecificClasses;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class MenuEdit extends Menu
{
    private Menu menuEdit;
    
    public Menu getMenuEdit()
    {
        return menuEdit;
    }
    
    public MenuEdit()
    {
        SpecificClasses.initSubClasses();
        
        menuEdit = new Menu(LANG.getTranslation("menu.edit"));
        
        MenuItem undo = new MenuItemTranslate("menu.edit.item.undo", "Ctrl+Z", (ActionEvent t) -> {});
        MenuItem redo = new MenuItemTranslate("menu.edit.item.redo", "Ctrl+Y", (ActionEvent t) -> {});
        
        MenuItem cut = new MenuItemTranslate("menu.edit.item.cut", "Ctrl+X", (ActionEvent t) -> {});
        MenuItem copy = new MenuItemTranslate("menu.edit.item.copy", "Ctrl+C", (ActionEvent t) -> {});
        MenuItem paste = new MenuItemTranslate("menu.edit.item.paste", "Ctrl+V", (ActionEvent t) -> {});
        MenuItem delete = new MenuItemTranslate("menu.edit.item.delete", "Del", (ActionEvent t) -> {});
        MenuItem selectAll = new MenuItemTranslate("menu.edit.item.selectAll", "Ctrl+A", (ActionEvent t) -> {});
        
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
        menuEdit.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete, selectAll, new SeparatorMenuItem(), newClass, newSpecifiedClass);
    }
}