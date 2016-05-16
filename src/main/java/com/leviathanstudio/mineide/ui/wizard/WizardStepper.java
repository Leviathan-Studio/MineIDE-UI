package com.leviathanstudio.mineide.ui.wizard;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WizardStepper extends StackPane
{
    private final HBox         root;
    private final WizardDialog dialog;

    public WizardStepper(WizardDialog dialog)
    {
        this.dialog = dialog;
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.setPrefHeight(52);
        this.setStyle("-fx-border-style: hidden hidden solid hidden;-fx-border-color: #E0E0E0;-fx-border-width: 2px;");

        this.root = new HBox();
        this.root.setAlignment(Pos.CENTER);
        this.getChildren().add(this.root);
    }

    public void updateStepper()
    {
        this.root.getChildren().clear();
        for (WizardStep step : this.dialog.getSteps())
        {
            StackPane icon = new StackPane();
            Circle circle = null;
            if (this.dialog.getCurrentStep() < this.dialog.getSteps().indexOf(step))
            {
                circle = new Circle(12, Color.web("BLACK"));
                circle.setStyle("-fx-opacity: 0.38;");
            }
            else
                circle = new Circle(12, Color.web("#2196F3"));
            icon.getChildren().add(circle);

            if (this.dialog.getCurrentStep() > this.dialog.getSteps().indexOf(step))
            {
                GlyphIcon check = GlyphsBuilder.create(MaterialDesignIconView.class).glyph(MaterialDesignIcon.CHECK)
                        .size("2em").build();
                check.getStyleClass().add("wizard-valid");
                icon.getChildren().add(check);
            }
            else
            {
                Label number = new Label(String.valueOf(this.dialog.getSteps().indexOf(step) + 1));
                number.setStyle("-fx-font-family: Roboto;-fx-font-size: 14;-fx-text-fill: WHITE;");
                icon.getChildren().add(number);
            }
            icon.setPadding(new Insets(8));
            this.root.getChildren().add(icon);
        }
    }
}