package com.leviathanstudio.mineide.ui;

import com.leviathanstudio.mineide.ui.control.MenuItemIcon;
import com.leviathanstudio.mineide.ui.menu.MenuCategory;
import com.leviathanstudio.mineide.ui.menu.MenuManagement;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

public class GuiMenuBar extends GuiPart
{

    public GuiMenuBar()
    {
        super(new VBox());
    }

    @Override
    public void init()
    {
        // VBox box = new VBox();
        // GuiMain.root.getChildren().add(box);

        MenuBar menuBar = new MenuBar();
        MenuManagement menuManagement = new MenuManagement(menuBar);

        // File
        MenuCategory catFileProject = new MenuCategory("project");
        MenuCategory catFileSave = new MenuCategory("save");
        MenuCategory catFileClose = new MenuCategory("close");
        MenuCategory catFileExit = new MenuCategory("exit");

        // Edit
        MenuCategory catEditUndo = new MenuCategory("undo");
        MenuCategory catEditCcp = new MenuCategory("ccp");
        MenuCategory catEditSelect = new MenuCategory("undo");

        // Forge
        MenuCategory catForgeSetup = new MenuCategory("setup");

        // Minecraft
        MenuCategory catMinecraftLaunch = new MenuCategory("launch");
        MenuCategory catMinecraftOther = new MenuCategory("other");

        // Window
        MenuCategory catWindowShow = new MenuCategory("show");
        MenuCategory catWindowPreferences = new MenuCategory("preferences");

        // Help
        MenuCategory catHelpAbout = new MenuCategory("about");

        // File
        catFileProject.add(new MenuItemIcon("menu.file.item.newProject", "/mineIDE/img/addIcon.png", "Ctrl+Alt+P",
                (ActionEvent t) ->
                {
                }));
        catFileProject.add(new MenuItemIcon("menu.file.item.openProject", "", "Ctrl+Alt+O", (ActionEvent t) ->
        {
        }));
        catFileProject.add(new MenuItemIcon("menu.file.item.closeProject", "", "Ctrl+Alt+W", (ActionEvent t) ->
        {
        }));

        catFileSave.add(new MenuItemIcon("menu.file.item.save", "", "Ctrl+S", (ActionEvent t) ->
        {
        }));
        catFileSave.add(new MenuItemIcon("menu.file.item.saveAll", "", "Ctrl+Shift+S", (ActionEvent t) ->
        {
        }));

        catFileClose.add(new MenuItemIcon("menu.file.item.close", "", "Ctrl+W", (ActionEvent t) ->
        {
        }));
        catFileClose.add(new MenuItemIcon("menu.file.item.closeAll", "", "Ctrl+Shift+W", (ActionEvent t) ->
        {
        }));

        catFileExit.add(new MenuItemIcon("menu.file.item.exit", "", "Ctrl+Q", (ActionEvent t) ->
        {
        }));

        // Edit
        catEditUndo.add(new MenuItemIcon("menu.edit.item.undo", "", "Ctrl+Z", (ActionEvent t) ->
        {
        }));
        catEditUndo.add(new MenuItemIcon("menu.edit.item.redo", "", "Ctrl+Y", (ActionEvent t) ->
        {
        }));

        catEditCcp.add(new MenuItemIcon("menu.edit.item.cut", "", "Ctrl+X", (ActionEvent t) ->
        {
        }));
        catEditCcp.add(new MenuItemIcon("menu.edit.item.copy", "", "Ctrl+C", (ActionEvent t) ->
        {
        }));
        catEditCcp.add(new MenuItemIcon("menu.edit.item.paste", "", "Ctrl+V", (ActionEvent t) ->
        {
        }));

        catEditSelect.add(new MenuItemIcon("menu.edit.item.delete", "", "Del", (ActionEvent t) ->
        {
        }));
        catEditSelect.add(new MenuItemIcon("menu.edit.item.selectAll", "", "Ctrl+A", (ActionEvent t) ->
        {
        }));

        // Forge
        catForgeSetup.add(new MenuItemIcon("menu.forge.item.install", "", "Ctrl+Alt+F", (ActionEvent t) ->
        {
        }));
        catForgeSetup.add(new MenuItemIcon("menu.forge.item.forceUpdate", "", "Ctrl+Alt+U", (ActionEvent t) ->
        {
        }));
        catForgeSetup.add(new MenuItemIcon("menu.forge.item.build", "", "Ctrl+Alt+B", (ActionEvent t) ->
        {
        }));

        // Minecraft
        catMinecraftLaunch.add(new MenuItemIcon("menu.minecraft.item.runClient", "", "F10", (ActionEvent t) ->
        {
        }));
        catMinecraftLaunch.add(new MenuItemIcon("menu.minecraft.item.runServer", "", "F11", (ActionEvent t) ->
        {
        }));

        catMinecraftOther.add(new MenuItemIcon("menu.minecraft.item.screenshots", "", (ActionEvent t) ->
        {
        }));

        // Window
        catWindowShow.add(new MenuItemIcon("menu.window.item.hide_toolbar", "", (ActionEvent t) ->
        {
        }));

        catWindowPreferences.add(new MenuItemIcon("menu.window.item.preferences", "", (ActionEvent t) ->
        {
        }));

        // Help
        catHelpAbout.add(new MenuItemIcon("menu.help.item.about", "", "F1", (ActionEvent t) ->
        {
        }));

        // Menus
        menuManagement.addMenu("menu.file", catFileProject, catFileSave, catFileClose, catFileExit);
        menuManagement.addMenu("menu.edit", catEditUndo, catEditCcp, catEditSelect);
        menuManagement.addMenu("menu.forge", catForgeSetup);
        menuManagement.addMenu("menu.minecraft", catMinecraftLaunch, catMinecraftOther);
        menuManagement.addMenu("menu.window", catWindowShow, catWindowPreferences);
        menuManagement.addMenu("menu.help", catHelpAbout);
        this.addElement(menuBar);

    }

}
