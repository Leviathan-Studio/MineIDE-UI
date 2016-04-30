package com.leviathanstudio.mineide.ui.component;

import static com.leviathanstudio.mineide.main.Translation.LANG;

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
        setBlock(LANG.getTranslation("specificClass.block.block"));
        setItem(LANG.getTranslation("specificClass.item.item"));
        setEntity(LANG.getTranslation("specificClass.entity.entity"));
        setTileEntity(LANG.getTranslation("specificClass.tileEntity.tileEntity"));
        setDimension(LANG.getTranslation("specificClass.dim.dim"));
        
        // Blocks
        setBasicBlock(new MenuItem(LANG.getTranslation("specificClass.block.basic")));
        setOreBlock(new MenuItem(LANG.getTranslation("specificClass.block.ore")));
        
        // Items
        setBasicItem(new MenuItem(LANG.getTranslation("specificClass.item.basic")));
        setArmorItem(new MenuItem(LANG.getTranslation("specificClass.item.armor")));
        setToolItem(new MenuItem(LANG.getTranslation("specificClass.item.tool")));
        setWeaponItem(new MenuItem(LANG.getTranslation("specificClass.item.weapon")));
        
        // Entity
        setBasicEntity(new MenuItem(LANG.getTranslation("specificClass.entity.basic")));
        setAnimalEntity(new MenuItem(LANG.getTranslation("specificClass.entity.passive")));
        setMonsterEntity(new MenuItem(LANG.getTranslation("specificClass.entity.monster")));
        setWaterEntity(new MenuItem(LANG.getTranslation("specificClass.entity.water")));
        setMountEntity(new MenuItem(LANG.getTranslation("specificClass.entity.mount")));
        setBossEntity(new MenuItem(LANG.getTranslation("specificClass.entity.boss")));
        
        // TileEntity
        setBasicTileEntity(new MenuItem(LANG.getTranslation("specificClass.tileEntity.tileEntity")));
        setTileEntityTesr(new MenuItem(LANG.getTranslation("specificClass.tileEntity.tesr")));
        
        // Dimension
        setDimOverworld(new MenuItem(LANG.getTranslation("specificClass.dim.overworld")));
        setDimNether(new MenuItem(LANG.getTranslation("specificClass.dim.nether")));
        setDimEnd(new MenuItem(LANG.getTranslation("specificClass.dim.end")));
    }
    
    public static String getBlock()
    {
        return block;
    }
    
    public static void setBlock(String block)
    {
        SpecificClasses.block = block;
    }
    
    public static String getItem()
    {
        return item;
    }
    
    public static void setItem(String item)
    {
        SpecificClasses.item = item;
    }
    
    public static String getEntity()
    {
        return entity;
    }
    
    public static void setEntity(String entity)
    {
        SpecificClasses.entity = entity;
    }
    
    public static String getTileEntity()
    {
        return tileEntity;
    }
    
    public static void setTileEntity(String tileEntity)
    {
        SpecificClasses.tileEntity = tileEntity;
    }
    
    public static String getDimension()
    {
        return dimension;
    }
    
    public static void setDimension(String dimension)
    {
        SpecificClasses.dimension = dimension;
    }
    
    public static MenuItem getBasicBlock()
    {
        basicBlock.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("BASIC BLOCK");
            }
        });
        
        return basicBlock;
    }
    
    public static void setBasicBlock(MenuItem basicBlock)
    {
        SpecificClasses.basicBlock = basicBlock;
    }
    
    public static MenuItem getOreBlock()
    {
        oreBlock.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
            
            }
        });
        
        return oreBlock;
    }
    
    public static void setOreBlock(MenuItem oreBlock)
    {
        SpecificClasses.oreBlock = oreBlock;
    }
    
    public static MenuItem getBasicItem()
    {
        return basicItem;
    }
    
    public static void setBasicItem(MenuItem basicItem)
    {
        SpecificClasses.basicItem = basicItem;
    }
    
    public static MenuItem getArmorItem()
    {
        return armorItem;
    }
    
    public static void setArmorItem(MenuItem armorItem)
    {
        SpecificClasses.armorItem = armorItem;
    }
    
    public static MenuItem getToolItem()
    {
        return toolItem;
    }
    
    public static void setToolItem(MenuItem toolItem)
    {
        SpecificClasses.toolItem = toolItem;
    }
    
    public static MenuItem getWeaponItem()
    {
        return weaponItem;
    }
    
    public static void setWeaponItem(MenuItem weaponItem)
    {
        SpecificClasses.weaponItem = weaponItem;
    }
    
    public static MenuItem getBasicEntity()
    {
        return basicEntity;
    }
    
    public static void setBasicEntity(MenuItem basicEntity)
    {
        SpecificClasses.basicEntity = basicEntity;
    }
    
    public static MenuItem getAnimalEntity()
    {
        return animalEntity;
    }
    
    public static void setAnimalEntity(MenuItem animalEntity)
    {
        SpecificClasses.animalEntity = animalEntity;
    }
    
    public static MenuItem getMonsterEntity()
    {
        return monsterEntity;
    }
    
    public static void setMonsterEntity(MenuItem monsterEntity)
    {
        SpecificClasses.monsterEntity = monsterEntity;
    }
    
    public static MenuItem getWaterEntity()
    {
        return waterEntity;
    }
    
    public static void setWaterEntity(MenuItem waterEntity)
    {
        SpecificClasses.waterEntity = waterEntity;
    }
    
    public static MenuItem getMountEntity()
    {
        return mountEntity;
    }
    
    public static void setMountEntity(MenuItem mountEntity)
    {
        SpecificClasses.mountEntity = mountEntity;
    }
    
    public static MenuItem getBossEntity()
    {
        return bossEntity;
    }
    
    public static void setBossEntity(MenuItem bossEntity)
    {
        SpecificClasses.bossEntity = bossEntity;
    }
    
    public static MenuItem getBasicTileEntity()
    {
        return basicTileEntity;
    }
    
    public static void setBasicTileEntity(MenuItem basicTileEntity)
    {
        SpecificClasses.basicTileEntity = basicTileEntity;
    }
    
    public static MenuItem getTileEntityTesr()
    {
        return tileEntityTesr;
    }
    
    public static void setTileEntityTesr(MenuItem tileEntityTesr)
    {
        SpecificClasses.tileEntityTesr = tileEntityTesr;
    }
    
    public static MenuItem getDimOverworld()
    {
        return dimOverworld;
    }
    
    public static void setDimOverworld(MenuItem dimOverworld)
    {
        SpecificClasses.dimOverworld = dimOverworld;
    }
    
    public static MenuItem getDimNether()
    {
        return dimNether;
    }
    
    public static void setDimNether(MenuItem dimNether)
    {
        SpecificClasses.dimNether = dimNether;
    }
    
    public static MenuItem getDimEnd()
    {
        return dimEnd;
    }
    
    public static void setDimEnd(MenuItem dimEnd)
    {
        SpecificClasses.dimEnd = dimEnd;
    }
    
}