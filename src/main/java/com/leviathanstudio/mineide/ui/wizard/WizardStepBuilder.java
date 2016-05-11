package com.leviathanstudio.mineide.ui.wizard;

import java.io.File;
import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.controls.IconLabel;
import com.leviathanstudio.mineide.utils.Utils;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * A simplified builder to add predefined elements to multiple wizard steps.
 * 
 * @author Ourten
 *
 */
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
        JFXComboBox<IconLabel> jfxCombo = new JFXComboBox<>();

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

        for (int i = 0; i < options.length; i++)
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

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addFileChooser(String fieldName, String fileChooseLabel, String startDir,
            FileChooser.ExtensionFilter... filters)
    {
        HBox box = new HBox();
        JFXButton button = new JFXButton(fileChooseLabel);
        button.setStyle("-fx-text-fill: BLACK;-fx-font-size: 18px;-fx-opacity: 0.7;");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(fileChooseLabel);
        fileChooser.setInitialDirectory(new File(startDir));
        fileChooser.getExtensionFilters().addAll(filters);
        current.getData().put(fieldName, new SimpleObjectProperty<File>());

        button.setOnAction(e -> current.getData().get(fieldName).setValue(fileChooser.showOpenDialog(Gui.mainStage)));

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(button, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);

        JFXTextField text = new JFXTextField();
        text.setEditable(false);
        current.getData().get(fieldName).addListener(new ChangeListener<File>()
        {
            @Override
            public void changed(ObservableValue<? extends File> observable, File oldValue, File newValue)
            {
                text.setText(newValue.getAbsolutePath());
            }
        });

        box.getChildren().addAll(text, button);
        current.add(box, 1, current.getData().size() - 1);
        return this;
    }

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addFileChoosers(String fieldName, String fileChooseLabel, String startDir,
            FileChooser.ExtensionFilter... filters)
    {
        HBox box = new HBox();
        JFXButton button = new JFXButton(fileChooseLabel);
        button.setStyle("-fx-text-fill: BLACK;-fx-font-size: 18px;-fx-opacity: 0.7;");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(fileChooseLabel);
        fileChooser.setInitialDirectory(new File(startDir));
        fileChooser.getExtensionFilters().addAll(filters);
        current.getData().put(fieldName, new SimpleSetProperty<File>());

        button.setOnAction(
                e -> current.getData().get(fieldName).setValue(fileChooser.showOpenMultipleDialog(Gui.mainStage)));

        Label label = new Label(fieldName);
        GridPane.setHalignment(label, HPos.RIGHT);
        GridPane.setHalignment(button, HPos.LEFT);
        current.add(label, 0, current.getData().size() - 1);

        JFXTextField text = new JFXTextField();
        text.setEditable(false);
        ((SimpleSetProperty<File>) current.getData().get(fieldName)).addListener(new SetChangeListener<File>()
        {
            @Override
            public void onChanged(SetChangeListener.Change<? extends File> change)
            {
                text.setText("");
                change.getSet().forEach(file -> text.appendText(file.getAbsolutePath() + ", "));
                text.setText(text.getText().substring(0, text.getText().length() - 2));
            }
        });

        box.getChildren().addAll(text, button);
        current.add(box, 1, current.getData().size() - 1);
        return this;
    }

    @SuppressWarnings("unchecked")
    public WizardStepBuilder addStringList(String fieldName, String promptText, String... defaultValues)
    {
        ScrollPane pane = new ScrollPane();
        VBox box = new VBox();
        VBox innerBox = new VBox();
        JFXButton createButton = new JFXButton();
        createButton.setGraphic(new ImageView(new Image(Utils.IMG_DIR + "/addIcon.png")));

        pane.setContent(box);
        current.getData().put(fieldName, new SimpleListProperty<SimpleStringProperty>());
        ((SimpleListProperty<SimpleStringProperty>) current.getData().get(fieldName))
                .setValue(FXCollections.observableArrayList());
        for (String value : defaultValues)
        {
            HBox box2 = new HBox();
            JFXTextField textField = new JFXTextField(value);
            textField.setPromptText(promptText);
            JFXButton deleteButton = new JFXButton();

            deleteButton.setOnAction(e -> innerBox.getChildren().remove(box2));
            box2.getChildren().addAll(textField, deleteButton);
            innerBox.getChildren().add(box2);
        }
        createButton.setOnAction(e ->
        {
            HBox box2 = new HBox();
            JFXTextField textField = new JFXTextField();
            textField.setPromptText(promptText);

            SimpleStringProperty textProperty = new SimpleStringProperty();
            textProperty.bind(textField.textProperty());
            ((SimpleListProperty<SimpleStringProperty>) current.getData().get(fieldName)).add(textProperty);
            JFXButton deleteButton = new JFXButton();
            deleteButton.setGraphic(new ImageView(new Image(Utils.IMG_DIR + "/trashBinIcon.png")));
            deleteButton.setOnAction(e2 ->
            {
                ((SimpleListProperty<SimpleStringProperty>) current.getData().get(fieldName)).remove(textProperty);
                innerBox.getChildren().remove(box2);
            });
            box2.getChildren().addAll(textField, deleteButton);
            innerBox.getChildren().add(box2);
        });
        box.getChildren().add(innerBox);
        box.getChildren().add(createButton);
        current.add(pane, 1, current.getData().size() - 1);
        return this;
    }

    public ArrayList<WizardStep> build()
    {
        return steps;
    }
}