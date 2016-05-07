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
    private String                stepName;
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

    public Map<String, Property> getData()
    {
        return data;
    }

    public void setData(Map<String, Property> data)
    {
        this.data = data;
    }

    public void addToValidate(JFXTextField field)
    {
        this.toValidate.add(field);
    }

    public boolean isValidated()
    {
        boolean rtn = true;

        for (JFXTextField field : this.toValidate)
        {
            if (!field.validate())
                rtn = false;
        }
        return rtn;
    }

    public String getStepName()
    {
        return stepName;
    }

    public void setStepName(String stepName)
    {
        this.stepName = stepName;
    }
}