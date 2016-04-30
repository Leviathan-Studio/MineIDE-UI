package com.leviathanstudio.mineide.ui.component;

import static com.leviathanstudio.mineide.main.Translation.LANG;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class MenuItemTranslate extends MenuItem
{
    public MenuItemTranslate(String text, String shortKey, EventHandler<ActionEvent> event)
    {
        this(text, event);
        this.setAccelerator(KeyCombination.keyCombination(shortKey));
    }
    
    public MenuItemTranslate(String text, EventHandler<ActionEvent> event)
    {
        this(text);
        this.setOnAction(event);
    }
    
    public MenuItemTranslate(String text)
    {
        super(LANG.getTranslation(text));
    }
}
