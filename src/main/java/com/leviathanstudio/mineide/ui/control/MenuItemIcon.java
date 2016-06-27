package com.leviathanstudio.mineide.ui.control;

import com.leviathanstudio.mineide.util.Translation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class MenuItemIcon extends MenuItem
{
    private final int ICON_SIZE = 16;

    private ImageView icon;

    public MenuItemIcon(String text, String iconPath, String shortKey, EventHandler<ActionEvent> event)
    {
        this(text, iconPath, event);
        this.setAccelerator(KeyCombination.keyCombination(shortKey));
    }

    public MenuItemIcon(String text, String iconPath, EventHandler<ActionEvent> event)
    {
        this(text, iconPath);
        this.setOnAction(event);
    }

    public MenuItemIcon(String text, String iconPath)
    {
        super(Translation.LANG.getTranslation(text));

        if (iconPath.isEmpty())
            iconPath = "/mineIDE/img/empty.png";

        this.icon = new ImageView(MenuItemIcon.class.getResource(iconPath).toString());
        this.icon.setFitWidth(this.ICON_SIZE);
        this.icon.setFitHeight(this.ICON_SIZE);

        this.setGraphic(this.icon);
    }
}