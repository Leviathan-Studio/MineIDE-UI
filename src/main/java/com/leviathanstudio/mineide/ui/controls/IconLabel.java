package com.leviathanstudio.mineide.ui.controls;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class IconLabel extends HBox
{
    private Label label;

    public IconLabel(Node icon, String text)
    {
        this.label = new Label(text);

        this.setSpacing(5);

        if (icon != null)
            this.getChildren().add(icon);
        this.getChildren().add(this.label);
    }

    @Override
    public String toString()
    {
        return this.label.getText();
    }
}