package com.leviathanstudio.mineide.ui.frame.minecraft;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.controls.IconLabel;
import com.leviathanstudio.mineide.ui.wizard.WizardDialog;
import com.leviathanstudio.mineide.ui.wizard.WizardStepBuilder;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Toggle;

public class PopupCreateProject
{
    public static void init()
    {
        // TODO: Redo the real wizard
        WizardDialog wizard = new WizardDialog("Project Creation", Gui.root);
        GlyphIcon<?> alert = GlyphsBuilder.create(MaterialDesignIconView.class).glyph(MaterialDesignIcon.ALERT_OCTAGON)
                .size("2em").build();
        wizard.addStep(
                new WizardStepBuilder().addStep("First").addString("test", "default value", "je suis un prompt")
                        .addBoolean("test2", false, "je suis un prompt")
                        .addEnum("Enum", 0, "Prompt", new IconLabel(alert, "Java 1.8"),
                                new IconLabel(alert, "Java 1.7"))
                        .addBigString("Description", "", "The description of your mod")
                        .addToggleGroup("Toggle", new String[] { "Oui", "Non", "Nosé" },
                                new String[] { "Lala", "Lal", "Lele" }, 0)
                        .addStep("Second step").addNumber("Age", 1, "Write your age")
                        .addFileChooser("File", "FileLabel", System.getProperty("user.home"))
                        .addStringList("Authors", "Authors of your mod").build());
        wizard.setOnWizardCompleted(e ->
        {
            System.out.println("Test: " + e.getSteps().get(0).getData().get("test").getValue() + " | "
                    + e.getSteps().get(0).getData().get("Enum").getValue());
            System.out.println(
                    "Hey : " + ((Toggle) e.getSteps().get(0).getData().get("Toggle").getValue()).getUserData());
            System.out.println("File : " + e.getSteps().get(1).getData().get("File").getValue());
            ((SimpleListProperty<SimpleStringProperty>) e.getSteps().get(1).getData().get("Authors")).get()
                    .forEach(value -> System.out.println("Author : " + value.getValue()));
        });
        wizard.showWizard();
    }
}