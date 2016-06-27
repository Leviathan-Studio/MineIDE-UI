package com.leviathanstudio.mineide.ui.control;

import com.leviathanstudio.mineide.util.Translation;

import javafx.scene.control.Menu;

public class MenuTranslate extends Menu
{
    public MenuTranslate(String text)
    {
        super(Translation.LANG.getTranslation(text));
    }
}
