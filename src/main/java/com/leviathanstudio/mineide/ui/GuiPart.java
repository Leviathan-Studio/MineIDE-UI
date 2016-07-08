package com.leviathanstudio.mineide.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class GuiPart extends Gui
{
    public Pane root;
    public Pane parent;

    public GuiPart(Pane root, Pane parent)
    {
        this.root = root;
        this.parent = parent;
    }

    public void addElement(Node n)
    {
        this.root.getChildren().add(n);
    }

    public void addElements(Node... n)
    {
        this.root.getChildren().addAll(n);
    }
}
