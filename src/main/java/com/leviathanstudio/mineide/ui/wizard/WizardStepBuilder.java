package com.leviathanstudio.mineide.ui.wizard;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
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

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        step.add(label, 0, step.getData().size() - 1);
        step.add(text, 1, step.getData().size() - 1);
        return this;
    }

    public WizardStep build()
    {
        return step;
    }
}