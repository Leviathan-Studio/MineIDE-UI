package com.leviathanstudio.mineide.ui.wizard;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.leviathanstudio.mineide.main.Translation;
import com.leviathanstudio.mineide.ui.wizard.events.WizardEvent;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

/**
 * A WizardDialog represent a wizard collecting various user input into multiple
 * phases.
 * 
 * @author Ourten
 *
 */
public class WizardDialog
{
    private String              title;
    @Getter
    @Setter
    private boolean             isCancellable;
    @Getter
    @Setter
    private int                 currentStep;
    private StackPane           header;
    private BorderPane          footer;
    private VBox                content;
    private JFXButton           nextButton;
    private JFXButton           cancelButton;
    private JFXButton           previousButton;
    private Label               titleLabel;
    @Getter
    @Setter
    private List<WizardStep>    steps;
    private final WizardStepper stepper;
    @Getter
    private final JFXDialog     dialog;
    @Getter
    private final BorderPane    region;

    /**
     * Wizard dialog constructor
     * 
     * @param root
     *            the root component from which the wizard will appear. Use a
     *            {@link StackPane}
     */
    public WizardDialog(String name, StackPane root)
    {
        stepper = new WizardStepper(this);
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
        this.region.setMinSize(700, 400);
        this.region.setPrefWidth(700);
        this.region.setMaxWidth(700);

        // Header
        this.header = new StackPane();
        titleLabel = new Label(this.title);
        titleLabel.setPadding(new Insets(24, 24, 20, 24));
        titleLabel.setFont(new Font("Arial", 16));
        this.header.setAlignment(Pos.CENTER_LEFT);
        titleLabel.setStyle("-fx-font-weight: BOLD;-fx-text-fill: WHITE;");
        this.header.getChildren().add(titleLabel);
        this.header.getStyleClass().add("wizard-heading");

        // Footer
        this.footer = new BorderPane();

        cancelButton = new JFXButton(Translation.LANG.getTranslation("button.cancel"));
        cancelButton.setOnAction(e ->
        {
            onWizardCancelledProperty.get()
                    .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.CANCELLED));
            dialog.close();
        });
        cancelButton.setStyle("-fx-text-fill: BLACK;-fx-font-size: 18px;-fx-opacity: 0.7;");

        nextButton = new JFXButton(Translation.LANG.getTranslation("button.next"));
        nextButton.setStyle("-fx-text-fill: #1E88E5;-fx-font-size: 18px;");
        nextButton.setOnAction(e ->
        {
            if (!this.steps.get(this.currentStep).isValidated())
                return;
            if ((this.currentStep + 1) == this.steps.size())
            {
                onWizardCompletedProperty.get()
                        .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.COMPLETED));
                this.currentStep = 0;
                this.dialog.close();
            }
            else
            {
                onWizardStepChangeBeforeProperty.get()
                        .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.STEP_CHANGE_BEFORE));
                switchStep(this.currentStep + 1);
                onWizardStepChangeAfterProperty.get()
                        .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.STEP_CHANGE_AFTER));
            }
        });

        previousButton = new JFXButton(Translation.LANG.getTranslation("button.back"));
        previousButton.setStyle("-fx-text-fill: #1E88E5;-fx-font-size: 18px;");
        previousButton.setOnAction(e ->
        {
            onWizardStepChangeBeforeProperty.get()
                    .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.STEP_CHANGE_BEFORE));
            switchStep(this.currentStep - 1);
            onWizardStepChangeAfterProperty.get()
                    .handle(new WizardEvent(this.currentStep, this.steps, WizardEvent.STEP_CHANGE_AFTER));
        });

        HBox rightFooter = new HBox();
        rightFooter.setAlignment(Pos.CENTER_RIGHT);
        rightFooter.getChildren().add(cancelButton);
        rightFooter.getChildren().add(nextButton);

        this.footer.setPadding(new Insets(12));
        this.footer.setLeft(previousButton);
        this.footer.setRight(rightFooter);
    }

    public void switchStep(int newStep)
    {
        if (this.content.getChildren().contains(this.steps.get(currentStep)))
            this.content.getChildren().remove(this.steps.get(currentStep));
        this.currentStep = newStep;
        this.titleLabel.setText(title + " - " + this.steps.get(currentStep).getStepName());
        this.content.getChildren().add(this.steps.get(currentStep));
        if (this.steps.size() == (this.currentStep + 1))
        {
            this.nextButton.setText(Translation.LANG.getTranslation("button.finish"));
            this.nextButton.setStyle(
                    "-fx-button-type: RAISED;-fx-background-color: #1E88E5;-fx-text-fill: WHITE;-fx-font-size: 18px;");
        }
        else
        {
            this.nextButton.setText(Translation.LANG.getTranslation("button.next"));
            this.nextButton.setStyle("-fx-text-fill: #1E88E5;-fx-font-size: 18px;");
        }
        if (this.currentStep == 0)
            this.previousButton.setDisable(true);
        else
            this.previousButton.setDisable(false);
        if (this.stepper != null)
            this.stepper.updateStepper();
    }

    public void showWizard()
    {
        this.content.getChildren().add(this.header);
        if (this.getSteps().size() > 1)
            this.content.getChildren().add(this.stepper);
        switchStep(this.currentStep);
        this.region.setTop(this.content);
        this.region.setBottom(this.footer);
        cancelButton.setDisable(!this.isCancellable);
        dialog.show();
    }

    public void hideWizard()
    {
        dialog.close();
    }

    public void addStep(WizardStep... step)
    {
        this.steps.addAll(Arrays.asList(step));
    }

    public void addStep(List<WizardStep> step)
    {
        this.steps.addAll(step);
    }

    // EVENTS

    private ObjectProperty<EventHandler<? super WizardEvent>> onWizardCompletedProperty = new SimpleObjectProperty<>(
            (completed) ->
            {
            });

    public void setOnWizardCompleted(EventHandler<? super WizardEvent> handler)
    {
        onWizardCompletedProperty.set(handler);
    }

    public void getOnWizardCompleted(EventHandler<? super WizardEvent> handler)
    {
        onWizardCompletedProperty.get();
    }

    private ObjectProperty<EventHandler<? super WizardEvent>> onWizardCancelledProperty = new SimpleObjectProperty<>(
            (cancelled) ->
            {
            });

    public void setOnWizardCancelled(EventHandler<? super WizardEvent> handler)
    {
        onWizardCancelledProperty.set(handler);
    }

    public void getOnWizardCancelled(EventHandler<? super WizardEvent> handler)
    {
        onWizardCancelledProperty.get();
    }

    private ObjectProperty<EventHandler<? super WizardEvent>> onWizardStepChangeBeforeProperty = new SimpleObjectProperty<>(
            (stepchange) ->
            {
            });

    public void setOnWizardStepChangeBefore(EventHandler<? super WizardEvent> handler)
    {
        onWizardStepChangeBeforeProperty.set(handler);
    }

    public void getOnWizardStepChangeBefore(EventHandler<? super WizardEvent> handler)
    {
        onWizardStepChangeBeforeProperty.get();
    }

    private ObjectProperty<EventHandler<? super WizardEvent>> onWizardStepChangeAfterProperty = new SimpleObjectProperty<>(
            (stepchange) ->
            {
            });

    public void setOnWizardStepChangeAfter(EventHandler<? super WizardEvent> handler)
    {
        onWizardStepChangeAfterProperty.set(handler);
    }

    public void getOnWizardStepChangeAfter(EventHandler<? super WizardEvent> handler)
    {
        onWizardStepChangeAfterProperty.get();
    }
}