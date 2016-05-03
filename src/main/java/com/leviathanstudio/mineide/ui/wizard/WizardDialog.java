package com.leviathanstudio.mineide.ui.wizard;

import java.util.List;

import com.google.common.collect.Lists;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * A WizardDialog represent a wizard collecting various user input into multiple
 * phases.
 * 
 * @author Ourten
 *
 */
public class WizardDialog
{
    private String           title;
    private boolean          isCancellable;
    private List<WizardStep> steps;
    private final JFXDialog dialog;
    private final VBox       region;

    /**
     * Wizard dialog constructor
     * 
     * @param root
     *            the root component from which the wizard will appear. Use a
     *            {@link StackPane}
     */
    public WizardDialog(String name, StackPane root)
    {
        steps = Lists.newArrayList();
        region = new VBox();
        dialog = new JFXDialog(root, region, DialogTransition.CENTER);
        title = name;
        initWizard();
    }

    private void initWizard()
    {
        dialog.setOverlayClose(false);

        StackPane titlePane = new StackPane();
        Label titleLabel = new Label(this.title);
        titleLabel.setPadding(new Insets(24, 24, 20, 24));
        titleLabel.setFont(new Font("Arial", 16));
        titlePane.setAlignment(Pos.CENTER_LEFT);
        titleLabel.setStyle("-fx-font-weight: BOLD;-fx-text-fill: WHITE;");
        titlePane.getChildren().add(titleLabel);
        titlePane.getStyleClass().add("wizard-heading");
        this.region.getChildren().add(titlePane);

        this.region.setMinSize(500, 400);
    }

    public void showWizard()
    {
        dialog.show();
    }

    public void hideWizard()
    {
        dialog.close();
    }

    public boolean isCancellable()
    {
        return isCancellable;
    }

    public void setCancellable(boolean isCancellable)
    {
        this.isCancellable = isCancellable;
    }

    public void addStep(WizardStep step)
    {
        this.steps.add(step);
        this.getRegion().getChildren().add(step);
    }

    public JFXDialog getDialog()
    {
        return dialog;
    }

    public VBox getRegion()
    {
        return region;
    }
}