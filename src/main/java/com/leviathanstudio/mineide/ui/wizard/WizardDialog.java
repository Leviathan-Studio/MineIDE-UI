package com.leviathanstudio.mineide.ui.wizard;

import java.util.List;

import com.google.common.collect.Lists;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
    private int              currentStep;
    private StackPane        header;
    private BorderPane       footer;
    private VBox             content;
    private JFXButton        nextButton;
    private JFXButton        cancelButton;
    private List<WizardStep> steps;
    private final JFXDialog  dialog;
    private final BorderPane region;

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
        region = new BorderPane();
        dialog = new JFXDialog(root, region, DialogTransition.CENTER);
        title = name;
        initWizard();
    }

    private void initWizard()
    {
        dialog.setOverlayClose(false);

        // Default values
        this.isCancellable = true;
        this.currentStep = 0;

        // Content
        this.content = new VBox();
        this.region.setMinSize(500, 400);

        // Header
        this.header = new StackPane();
        Label titleLabel = new Label(this.title);
        titleLabel.setPadding(new Insets(24, 24, 20, 24));
        titleLabel.setFont(new Font("Arial", 16));
        this.header.setAlignment(Pos.CENTER_LEFT);
        titleLabel.setStyle("-fx-font-weight: BOLD;-fx-text-fill: WHITE;");
        this.header.getChildren().add(titleLabel);
        this.header.getStyleClass().add("wizard-heading");

        // Footer
        this.footer = new BorderPane();

        cancelButton = new JFXButton("CANCEL");
        cancelButton.setOnAction(e -> dialog.close());
        cancelButton.setStyle(
                "-fx-text-fill: #EF5350;-fx-font-size: 18px;");
        nextButton = new JFXButton("NEXT");
        nextButton.setStyle("-fx-text-fill: #1E88E5;-fx-font-size: 18px");

        nextButton.setAlignment(Pos.CENTER_LEFT);
        this.footer.setPadding(new Insets(12));
        this.footer.setLeft(cancelButton);
        this.footer.setRight(nextButton);
    }

    public void showWizard()
    {
        this.content.getChildren().add(this.header);
        this.content.getChildren().add(this.steps.get(currentStep));
        this.region.setTop(this.content);
        this.region.setBottom(this.footer);
        nextButton.setText(this.steps.size() == 1 ? "FINISH" : "NEXT");
        cancelButton.setDisable(!this.isCancellable);
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

    /**
     * 
     * @param isCancellable
     *            : choose if the wizard can be cancelled, by default yes.
     */
    public void setCancellable(boolean isCancellable)
    {
        this.isCancellable = isCancellable;
    }

    public void addStep(WizardStep step)
    {
        this.steps.add(step);
    }

    public JFXDialog getDialog()
    {
        return dialog;
    }

    public BorderPane getRegion()
    {
        return region;
    }
}