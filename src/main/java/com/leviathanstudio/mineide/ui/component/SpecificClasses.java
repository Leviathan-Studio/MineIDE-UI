package com.leviathanstudio.mineide.ui.component;

import com.leviathanstudio.mineide.main.Translation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class SpecificClasses
{
    private static String block, item, entity, tileEntity, dimension;
    
    // Blocks
    private static MenuItem basicBlock, oreBlock;
    // Items
    private static MenuItem basicItem, armorItem, toolItem, weaponItem;
    // Entity
    private static MenuItem basicEntity, animalEntity, monsterEntity, waterEntity, mountEntity, bossEntity;
    // TileEntity
    private static MenuItem basicTileEntity, tileEntityTesr;
    // Dimension
    private static MenuItem dimOverworld, dimNether, dimEnd;
    
    public static void initSubClasses()
    {
        setBlock(Translation.LANG.getTranslation("specificClass.block.block"));
        setItem(Translation.LANG.getTranslation("specificClass.item.item"));
        setEntity(Translation.LANG.getTranslation("specificClass.entity.entity"));
        setTileEntity(Translation.LANG.getTranslation("specificClass.tileEntity.tileEntity"));
        setDimension(Translation.LANG.getTranslation("specificClass.dim.dim"));
        
        // Blocks
        setBasicBlock(new MenuItem(Translation.LANG.getTranslation("specificClass.block.basic")));
        setOreBlock(new MenuItem(Translation.LANG.getTranslation("specificClass.block.ore")));
        
        // Items
        setBasicItem(new MenuItem(Translation.LANG.getTranslation("specificClass.item.basic")));
        setArmorItem(new MenuItem(Translation.LANG.getTranslation("specificClass.item.armor")));
        setToolItem(new MenuItem(Translation.LANG.getTranslation("specificClass.item.tool")));
        setWeaponItem(new MenuItem(Translation.LANG.getTranslation("specificClass.item.weapon")));
        
        // Entity
        setBasicEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.basic")));
        setAnimalEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.passive")));
        setMonsterEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.monster")));
        setWaterEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.water")));
        setMountEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.mount")));
        setBossEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.entity.boss")));
        
        // TileEntity
        setBasicTileEntity(new MenuItem(Translation.LANG.getTranslation("specificClass.tileEntity.tileEntity")));
        setTileEntityTesr(new MenuItem(Translation.LANG.getTranslation("specificClass.tileEntity.tesr")));
        
        // Dimension
        setDimOverworld(new MenuItem(Translation.LANG.getTranslation("specificClass.dim.overworld")));
        setDimNether(new MenuItem(Translation.LANG.getTranslation("specificClass.dim.nether")));
        setDimEnd(new MenuItem(Translation.LANG.getTranslation("specificClass.dim.end")));
    }
    
    public static String getBlock()
    {
        return SpecificClasses.block;
    }
    
    public static void setBlock(String block)
    {
        SpecificClasses.block = block;
    }
    
    public static String getItem()
    {
        return SpecificClasses.item;
    }
    
    public static void setItem(String item)
    {
        SpecificClasses.item = item;
    }
    
    public static String getEntity()
    {
        return SpecificClasses.entity;
    }
    
    public static void setEntity(String entity)
    {
        SpecificClasses.entity = entity;
    }
    
    public static String getTileEntity()
    {
        return SpecificClasses.tileEntity;
    }
    
    public static void setTileEntity(String tileEntity)
    {
        SpecificClasses.tileEntity = tileEntity;
    }
    
    public static String getDimension()
    {
        return SpecificClasses.dimension;
    }
    
    public static void setDimension(String dimension)
    {
        SpecificClasses.dimension = dimension;
    }
    
    public static MenuItem getBasicBlock()
    {
        SpecificClasses.basicBlock.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("BASIC BLOCK");
            }
        });
        
        return SpecificClasses.basicBlock;
    }
    
    public static void setBasicBlock(MenuItem basicBlock)
    {
        SpecificClasses.basicBlock = basicBlock;
    }
    
    public static MenuItem getOreBlock()
    {
        SpecificClasses.oreBlock.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                
            }
        });
        
        return SpecificClasses.oreBlock;
    }
    
    public static void setOreBlock(MenuItem oreBlock)
    {
        SpecificClasses.oreBlock = oreBlock;
    }
    
    public static MenuItem getBasicItem()
    {
        return SpecificClasses.basicItem;
    }
    
    public static void setBasicItem(MenuItem basicItem)
    {
        SpecificClasses.basicItem = basicItem;
    }
    
    public static MenuItem getArmorItem()
    {
        return SpecificClasses.armorItem;
    }
    
    public static void setArmorItem(MenuItem armorItem)
    {
        SpecificClasses.armorItem = armorItem;
    }
    
    public static MenuItem getToolItem()
    {
        return SpecificClasses.toolItem;
    }
    
    public static void setToolItem(MenuItem toolItem)
    {
        SpecificClasses.toolItem = toolItem;
    }
    
    public static MenuItem getWeaponItem()
    {
        return SpecificClasses.weaponItem;
    }
    
    public static void setWeaponItem(MenuItem weaponItem)
    {
        SpecificClasses.weaponItem = weaponItem;
    }
    
    public static MenuItem getBasicEntity()
    {
        return SpecificClasses.basicEntity;
    }
    
    public static void setBasicEntity(MenuItem basicEntity)
    {
        SpecificClasses.basicEntity = basicEntity;
    }
    
    public static MenuItem getAnimalEntity()
    {
        return SpecificClasses.animalEntity;
    }
    
    public static void setAnimalEntity(MenuItem animalEntity)
    {
        SpecificClasses.animalEntity = animalEntity;
    }
    
    public static MenuItem getMonsterEntity()
    {
        return SpecificClasses.monsterEntity;
    }
    
    public static void setMonsterEntity(MenuItem monsterEntity)
    {
        SpecificClasses.monsterEntity = monsterEntity;
    }
    
    public static MenuItem getWaterEntity()
    {
        return SpecificClasses.waterEntity;
    }
    
    public static void setWaterEntity(MenuItem waterEntity)
    {
        SpecificClasses.waterEntity = waterEntity;
    }
    
    public static MenuItem getMountEntity()
    {
        return SpecificClasses.mountEntity;
    }
    
    public static void setMountEntity(MenuItem mountEntity)
    {
        SpecificClasses.mountEntity = mountEntity;
    }
    
    public static MenuItem getBossEntity()
    {
        return SpecificClasses.bossEntity;
    }
    
    public static void setBossEntity(MenuItem bossEntity)
    {
        SpecificClasses.bossEntity = bossEntity;
    }
    
    public static MenuItem getBasicTileEntity()
    {
        return SpecificClasses.basicTileEntity;
    }
    
    public static void setBasicTileEntity(MenuItem basicTileEntity)
    {
        SpecificClasses.basicTileEntity = basicTileEntity;
    }
    
    public static MenuItem getTileEntityTesr()
    {
        return SpecificClasses.tileEntityTesr;
    }
    
    public static void setTileEntityTesr(MenuItem tileEntityTesr)
    {
        SpecificClasses.tileEntityTesr = tileEntityTesr;
    }
    
    public static MenuItem getDimOverworld()
    {
        return SpecificClasses.dimOverworld;
    }
    
    public static void setDimOverworld(MenuItem dimOverworld)
    {
        SpecificClasses.dimOverworld = dimOverworld;
    }
    
    public static MenuItem getDimNether()
    {
        return SpecificClasses.dimNether;
    }
    
    public static void setDimNether(MenuItem dimNether)
    {
        SpecificClasses.dimNether = dimNether;
    }
    
    public static MenuItem getDimEnd()
    {
        return SpecificClasses.dimEnd;
    }
    
    public static void setDimEnd(MenuItem dimEnd)
    {
        SpecificClasses.dimEnd = dimEnd;
    }
    
}