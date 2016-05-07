package com.leviathanstudio.mineide.ui.wizard;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.leviathanstudio.mineide.ui.controls.IconLabel;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class WizardStepBuilder
{
    private ArrayList<WizardStep> steps;
    private WizardStep            current;

    public WizardStepBuilder()
    {
        steps = Lists.newArrayList();
    }

    public WizardStepBuilder addStep(String stepName)
    {
        current = new WizardStep(stepName);
        steps.add(current);
        return this;
    }

    /**
     * Add a simple String to a wizard step.
     * 
     * @param fieldName
     * @param defaultValue
     *            the default String the textfield will contains.
     * @param prompt
     *            the text to show on the textfield prompt String.
     * @return
     */
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
        current.getData().put(fieldName, new SimpleStringProperty());
        current.getData().get(fieldName).bind(text.textProperty());
        current.addToValidate(text);

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(text, 1, current.getData().size() - 1);
        return this;
    }

    /**
     * Add a number only textfield to a wizard step. A {@link NumberValitor}
     * will be added to the textfield.
     * 
     * @param fieldName
     * @param defaultValue
     *            the default number value of the textfield.
     * @param prompt
     *            the text to show on the textfield prompt String.
     * @return
     */
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
        current.getData().put(fieldName, new SimpleStringProperty());
        current.getData().get(fieldName).bind(text.textProperty());
        current.addToValidate(text);

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(text, 1, current.getData().size() - 1);
        return this;
    }

    /**
     * Add a yes/no choice to a wizard step.
     * 
     * @param fieldName
     * @param defaultValue
     *            of the choice.
     * @param prompt
     *            the tooltip to show
     * @return
     */
    @SuppressWarnings("unchecked")
    public WizardStepBuilder addBoolean(String fieldName, boolean defaultValue, String prompt)
    {
        JFXCheckBox box = new JFXCheckBox();
        box.setTooltip(new Tooltip(prompt));
        box.setSelected(defaultValue);

        current.getData().put(fieldName, new SimpleBooleanProperty());
        current.getData().get(fieldName).bind(box.selectedProperty());

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(box, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(box, 1, current.getData().size() - 1);
        return this;
    }

    /**
     * Add an enumeration of options to a wizard step.
     * 
     * @param fieldName
     * @param defaultValue
     *            is the index of the value in your options array. Can be set <
     *            0 to empty the combobox as default.
     * @param prompt
     *            the tooltip to show
     * @param options
     *            your array of options, use a {@link IconLabel} object to add
     *            icons to your options. Only the text will be selected.
     * @return
     */
    @SuppressWarnings("unchecked")
    public WizardStepBuilder addEnum(String fieldName, int defaultValue, String prompt, IconLabel... options)
    {
        JFXComboBox<IconLabel> jfxCombo = new JFXComboBox<IconLabel>();

        jfxCombo.getItems().addAll(options);
        jfxCombo.setPromptText(prompt);

        if (defaultValue < 0)
            jfxCombo.setValue(new IconLabel(null, ""));
        else
            jfxCombo.setValue(options[defaultValue]);

        current.getData().put(fieldName, new SimpleObjectProperty<IconLabel>());
        current.getData().get(fieldName).bind(jfxCombo.valueProperty());

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(jfxCombo, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(jfxCombo, 1, current.getData().size() - 1);
        return this;
    }

    /**
     * Add a large String to a wizard step. A TextArea will be used to represent
     * it.
     * 
     * @param fieldName
     * @param defaultValue
     *            the default String the textfield will contains.
     * @param prompt
     *            the text to show on the textfield prompt String.
     * @return
     */
    @SuppressWarnings("unchecked")
    public WizardStepBuilder addBigString(String fieldName, String defaultValue, String prompt)
    {
        JFXTextArea text = new JFXTextArea();
        text.setPromptText(prompt);
        text.setText(defaultValue);
        current.getData().put(fieldName, new SimpleStringProperty());
        current.getData().get(fieldName).bind(text.textProperty());
        text.setMaxWidth(400);

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(text, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(text, 1, current.getData().size() - 1);
        return this;
    }

    /**
     * Add an enumeration of options to a wizard step. Multiple RadioButtons
     * will be used (horizontally-aligned).
     * 
     * @param fieldName
     * @param options
     *            list of choices.
     * @param prompt
     *            the text to show on the buttons tooltip.
     * @param defaultValue
     *            the default value to be selected.
     * @return
     */
    @SuppressWarnings("unchecked")
    public WizardStepBuilder addToggleGroup(String fieldName, String[] options, String[] prompt, int defaultValue)
    {
        final ToggleGroup group = new ToggleGroup();

        HBox box = new HBox();
        
        for(int i = 0; i < options.length; i++)
        {
            JFXRadioButton radio = new JFXRadioButton(options[i]);
            radio.setPadding(new Insets(10));
            radio.setToggleGroup(group);
            radio.setTooltip(new Tooltip(prompt[i]));
            radio.setUserData(options[i]);
            if (i == defaultValue)
                radio.setSelected(true);
            
            box.getChildren().add(radio);
            i++;
        }

        current.getData().put(fieldName, new ReadOnlyObjectWrapper<Toggle>());
        current.getData().get(fieldName).bind(group.selectedToggleProperty());
        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(box, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);
        current.add(box, 1, current.getData().size() - 1);
        return this;
    }

    public ArrayList<WizardStep> build()
    {
        return steps;
    }
}