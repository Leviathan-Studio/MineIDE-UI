package com.leviathanstudio.mineide.ui.control;

import com.jfoenix.controls.JFXButton;
import com.leviathanstudio.mineide.util.Util;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.text.Font;

public class IconButton extends JFXButton
{
    public static final int SIZE = 20;

    public IconButton(String icon)
    {
        super("", formatIcon(icon));
        this.setPrefSize(SIZE, SIZE);
        this.setFont(Font.font(SIZE / 4));
        this.setBorder(Border.EMPTY);

    }

    private static ImageView formatIcon(String path)
    {
        ImageView iv = new ImageView(Util.getResouce(path));
        iv.setFitWidth(SIZE);
        iv.setFitHeight(SIZE);
        return iv;
    }
}
