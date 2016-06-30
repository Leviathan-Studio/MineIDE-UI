package com.leviathanstudio.mineide.ui.control;

import com.leviathanstudio.mineide.util.Util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class MenuItemIcon extends MenuItemTranslate
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
        super(text);

        if (iconPath.isEmpty())
            iconPath = "/mineIDE/img/empty.png";

        this.icon = new ImageView(Util.getResouce(iconPath));
        this.icon.setFitWidth(this.ICON_SIZE);
        this.icon.setFitHeight(this.ICON_SIZE);

        this.setGraphic(this.icon);
    }
}