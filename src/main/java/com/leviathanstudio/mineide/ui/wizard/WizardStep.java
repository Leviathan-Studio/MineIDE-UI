package com.leviathanstudio.mineide.ui.wizard;

import java.util.Map;

import com.google.common.collect.Maps;

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
    private Map<String, Property> data;

    public WizardStep()
    {
        this.data = Maps.newHashMap();
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 24, 24, 24));
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
}