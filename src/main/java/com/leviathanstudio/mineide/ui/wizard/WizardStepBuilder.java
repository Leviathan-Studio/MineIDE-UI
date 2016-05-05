package com.leviathanstudio.mineide.ui.wizard;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class WizardStepBuilder
{
    private WizardStep step;

    public WizardStepBuilder(String stepName)
    {
        step = new WizardStep(stepName);
    }

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addString(String fieldName, String defaultValue, String prompt)
    {
        JFXTextField text = new JFXTextField();
        text.setPromptText(prompt);
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1em")
                .styleClass("error").build());
        text.getValidators().add(validator);
        text.focusedProperty().addListener((o, oldVal, newVal) ->
        {
            if (!newVal)
                text.validate();
        });
        text.setText(defaultValue);
        step.getData().put(fieldName, new SimpleStringProperty());
        step.getData().get(fieldName).bind(text.textProperty());
        step.addToValidate(text);

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        step.add(label, 0, step.getData().size() - 1);
        step.add(text, 1, step.getData().size() - 1);
        return this;
    }

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addNumber(String fieldName, Integer defaultValue, String prompt)
    {
        JFXTextField text = new JFXTextField();
        text.setPromptText(prompt);
        NumberValidator numberValidator = new NumberValidator();
        numberValidator.setMessage("Only numbers allowed");
        numberValidator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING)
                .size("1em").styleClass("error").build());
        text.getValidators().add(numberValidator);
        text.setOnKeyReleased(e ->
        {
            if (!text.getText().equals(""))
                text.validate();
        });
        text.setText(defaultValue.toString());
        step.getData().put(fieldName, new SimpleStringProperty());
        step.getData().get(fieldName).bind(text.textProperty());
        step.addToValidate(text);

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        step.add(label, 0, step.getData().size() - 1);
        step.add(text, 1, step.getData().size() - 1);
        return this;
    }

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addBoolean(String fieldName, boolean defaultValue, String prompt)
    {
        JFXCheckBox box = new JFXCheckBox();
        box.setTooltip(new Tooltip(prompt));
        box.setSelected(defaultValue);

        step.getData().put(fieldName, new SimpleBooleanProperty());
        step.getData().get(fieldName).bind(box.selectedProperty());

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(box, HPos.LEFT);
        step.add(label, 0, step.getData().size() - 1);
        step.add(box, 1, step.getData().size() - 1);
        return this;
    }

    public WizardStep build()
    {
        return step;
    }
}