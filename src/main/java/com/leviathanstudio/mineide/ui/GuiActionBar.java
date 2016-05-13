package com.leviathanstudio.mineide.ui;

import java.io.IOException;

import com.leviathanstudio.mineide.editor.CodeEditor;
import com.leviathanstudio.mineide.forge.ForgeHelper;
import com.leviathanstudio.mineide.forge.ForgeWorkspace;
import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.main.Translation;
import com.leviathanstudio.mineide.ui.component.SpecificClasses;
import com.leviathanstudio.mineide.ui.controls.MenuItemIcon;
import com.leviathanstudio.mineide.ui.controls.MenuItemTranslate;
import com.leviathanstudio.mineide.ui.frame.minecraft.PopupCreateProject;
import com.leviathanstudio.mineide.ui.frame.minecraft.ScreenshotsGallery;
import com.leviathanstudio.mineide.ui.frame.popup.PopupCredits;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;

public class GuiActionBar
{
    public static void init(Scene scene, VBox box)
    {
        MenuBar menuBar = new MenuBar();
        SpecificClasses.initSubClasses();

        menuBar.getMenus().addAll(createFileMenu(), createEditMenu(), createForgeMenu(), createOptionMenu(),
                createMinecraftMenu(), createRunMenu(), createHelpMenu());
        box.getChildren().add(menuBar);
    }

    private static Menu createFileMenu()
    {
        Menu menuFile = new Menu(Translation.LANG.getTranslation("menu.file"));

        MenuItem newProject = new MenuItemIcon("menu.file.item.newProject", "/mineIDE/img/addIcon.png", "Ctrl+Alt+P",
                (ActionEvent t) ->
                {
                    PopupCreateProject.init();
                });
        MenuItem openProject = new MenuItemIcon("menu.file.item.openProject", "", "Ctrl+Alt+O", (ActionEvent t) ->
        {
            GuiJavaEditor.tabBar.addTab("Test_" + (int) (Math.random() * 100) + ".java",
                    "Test_" + (int) (Math.random() * 100), new CodeEditor(""));
        });
        MenuItem closeProject = new MenuItemIcon("menu.file.item.closeProject", "", "Ctrl+Alt+W", (ActionEvent t) ->
        {
        });

        MenuItem save = new MenuItemIcon("menu.file.item.save", "", "Ctrl+S", (ActionEvent t) ->
        {
        });
        MenuItem saveAll = new MenuItemIcon("menu.file.item.saveAll", "", "Ctrl+Shift+S", (ActionEvent t) ->
        {
        });

        MenuItem close = new MenuItemIcon("menu.file.item.close", "", "Ctrl+W", (ActionEvent t) ->
        {
            GuiJavaEditor.tabBar.closeCurrentTab();
        });
        MenuItem closeAll = new MenuItemIcon("menu.file.item.closeAll", "", "Ctrl+Shift+W", (ActionEvent t) ->
        {
            TabHelper.closeAll();
        });

        MenuItem exit = new MenuItemIcon("menu.file.item.exit", "", "Ctrl+Q", (ActionEvent t) ->
        {
            MineIDE.instance.stop();

        });

        menuFile.getItems().addAll(newProject, openProject, closeProject, new SeparatorMenuItem(), save, saveAll,
                new SeparatorMenuItem(), close, closeAll, new SeparatorMenuItem(), exit);

        return menuFile;
    }

    private static Menu createEditMenu()
    {
        Menu menuEdit = new Menu(Translation.LANG.getTranslation("menu.edit"));

        MenuItem undo = new MenuItemIcon("menu.edit.item.undo", "", "Ctrl+Z", (ActionEvent t) ->
        {
        });
        MenuItem redo = new MenuItemIcon("menu.edit.item.redo", "", "Ctrl+Y", (ActionEvent t) ->
        {
        });

        MenuItem cut = new MenuItemIcon("menu.edit.item.cut", "", "Ctrl+X", (ActionEvent t) ->
        {
        });
        MenuItem copy = new MenuItemIcon("menu.edit.item.copy", "", "Ctrl+C", (ActionEvent t) ->
        {
        });
        MenuItem paste = new MenuItemIcon("menu.edit.item.paste", "", "Ctrl+V", (ActionEvent t) ->
        {
        });
        MenuItem delete = new MenuItemIcon("menu.edit.item.delete", "", "Del", (ActionEvent t) ->
        {
        });
        MenuItem selectAll = new MenuItemIcon("menu.edit.item.selectAll", "", "Ctrl+A", (ActionEvent t) ->
        {
        });

        menuEdit.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(),
                delete, selectAll);

        return menuEdit;
    }

    private static Menu createForgeMenu()
    {
        Menu menuForge = new Menu(Translation.LANG.getTranslation("menu.forge"));

        MenuItem installForge = new MenuItemIcon("menu.forge.item.install", "", "Ctrl+Alt+F", (ActionEvent t) ->
        {
            ForgeWorkspace.getInstance().installWorkspace();
        });
        MenuItem forceForgeUpdate = new MenuItemIcon("menu.forge.item.forceUpdate", "", "Ctrl+Alt+U", (ActionEvent t) ->
        {
            ForgeWorkspace.getInstance().forceUpdate();
        });

        MenuItem buildMod = new MenuItemIcon("menu.forge.item.build", "", "Ctrl+Alt+B", (ActionEvent t) ->
        {
            try
            {
                ForgeHelper.getInstance().compileToJar();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });

        // -----------

        MenuItem newClass = new MenuItemTranslate("menu.edit.item.newClass", "Ctrl+J", (ActionEvent t) ->
        {
        });

        // -----------
        Menu newSpecifiedClass = new Menu(Translation.LANG.getTranslation("menu.edit.item.newSpecificClass"));
        newSpecifiedClass.setOnAction((ActionEvent t) ->
        {
        });

        final String[] specificClass = new String[] { SpecificClasses.getBlock(), SpecificClasses.getItem(),
                SpecificClasses.getEntity(), SpecificClasses.getTileEntity(), SpecificClasses.getDimension() };

        for (String className : specificClass)
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

            if (className.toString().equals(SpecificClasses.getBlock()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicBlock(), SpecificClasses.getOreBlock());
            else if (className.toString().equals(SpecificClasses.getItem()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicItem(), SpecificClasses.getArmorItem(),
                        SpecificClasses.getToolItem(), SpecificClasses.getWeaponItem());
            else if (className.toString().equals(SpecificClasses.getEntity()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicEntity(), SpecificClasses.getAnimalEntity(),
                        SpecificClasses.getMonsterEntity(), SpecificClasses.getWaterEntity(),
                        SpecificClasses.getMountEntity(), SpecificClasses.getBossEntity());
            else if (className.toString().equals(SpecificClasses.getTileEntity()))
                classesMenu.getItems().addAll(SpecificClasses.getBasicTileEntity(),
                        SpecificClasses.getTileEntityTesr());
            else if (className.toString().equals(SpecificClasses.getDimension()))
                classesMenu.getItems().addAll(SpecificClasses.getDimOverworld(), SpecificClasses.getDimNether(),
                        SpecificClasses.getDimEnd());
        }

        menuForge.getItems().addAll(installForge, forceForgeUpdate, buildMod, new SeparatorMenuItem(), newClass,
                newSpecifiedClass);

        return menuForge;
    }

    private static Menu createOptionMenu()
    {
        Menu menuOption = new Menu(Translation.LANG.getTranslation("menu.options"));

        MenuItem menuPreferences = new MenuItemIcon("menu.options.item.preferences", "", (ActionEvent t) ->
        {
        });

        menuOption.getItems().addAll(menuPreferences);
        return menuOption;
    }

    private static Menu createRunMenu()
    {
        Menu menuRun = new Menu(Translation.LANG.getTranslation("menu.minecraft"));

        MenuItem menuRunClient = new MenuItemIcon("menu.minecraft.item.screenshots", "", (ActionEvent t) ->
        {
            try
            {
                new ScreenshotsGallery();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        menuRun.getItems().addAll(menuRunClient);
        return menuRun;
    }

    private static Menu createMinecraftMenu()
    {
        Menu menuRun = new Menu(Translation.LANG.getTranslation("menu.run"));

        MenuItem menuRunClient = new MenuItemIcon("menu.run.item.runClient", "", "F10", (ActionEvent t) ->
        {
            try
            {
                ForgeHelper.getInstance().runCommand("Client");
                System.out.println("Client Launched");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        MenuItem menuRunServer = new MenuItemIcon("menu.run.item.runServer", "", "F11", (ActionEvent t) ->
        {
            try
            {
                ForgeHelper.getInstance().runCommand("Server");
                System.out.println("Server Launched");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        menuRun.getItems().addAll(menuRunClient, menuRunServer);
        return menuRun;
    }

    private static Menu createHelpMenu()
    {
        Menu menuHelp = new Menu(Translation.LANG.getTranslation("menu.help"));

        MenuItem credits = new MenuItemIcon("menu.help.item.about", "", "F1", (ActionEvent t) ->
        {
            PopupCredits.init();
        });

        menuHelp.getItems().addAll(credits);

        return menuHelp;
    }
}