package com.leviathanstudio.mineide.ui.control;

import com.leviathanstudio.mineide.util.Util;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class IconButton extends Button
{
    public static final int SIZE = 30;

    public IconButton(String icon)
    {
        super("", formatIcon(icon));
        this.setPrefSize(SIZE - 4, SIZE - 4);
        this.setBorder(Border.EMPTY);

    }

    private static ImageView formatIcon(String path)
    {
        ImageView iv = new ImageView(Util.getResouce(path));
        iv.resize(SIZE, SIZE);
        return iv;
    }
}
