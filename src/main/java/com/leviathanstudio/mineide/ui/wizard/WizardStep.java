package com.leviathanstudio.mineide.ui.wizard;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

/**
 * A WizardStep object represent a step of one wizard. It contains informations
 * about the requireds informations and store the data collected when the step
 * end.
 *
 * @author Ourten
 *
 */
public class WizardStep extends GridPane
{
    @Getter
    @Setter
    private String                stepName;
    @Getter
    @Setter
    @SuppressWarnings("rawtypes")
    private Map<String, Property> data;
    private List<JFXTextField>    toValidate;

    public WizardStep(String stepName)
    {
        this.stepName = stepName;
        this.data = Maps.newHashMap();
        this.toValidate = Lists.newArrayList();
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(20, 24, 24, 24));
        this.setAlignment(Pos.CENTER);
    }

    public void addToValidate(JFXTextField field)
    {
        this.toValidate.add(field);
    }

    public boolean isValidated()
    {
        boolean rtn = true;

        for (JFXTextField field : this.toValidate)
            if (!field.validate())
                rtn = false;
        return rtn;
    }
}