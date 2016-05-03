package com.leviathanstudio.mineide.ui.controls;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Skin;

public class MDRoundButton extends JFXButton
{
    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new MDRoundButtonSkin(this);
    }
}