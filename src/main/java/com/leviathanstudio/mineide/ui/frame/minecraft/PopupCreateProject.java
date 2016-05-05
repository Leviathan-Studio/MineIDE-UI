package com.leviathanstudio.mineide.ui.frame.minecraft;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.controls.IconLabel;
import com.leviathanstudio.mineide.ui.wizard.WizardDialog;
import com.leviathanstudio.mineide.ui.wizard.WizardStepBuilder;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;

public class PopupCreateProject
{
    public static void init()
    {
        WizardDialog wizard = new WizardDialog("Project Creation", Gui.root);
        wizard.addStep(
                new WizardStepBuilder("First").addString("test", "default value", "je suis un prompt")
                        .addBoolean("test2", false,
                                "je suis un prompt")
                        .addEnum("Enum", 0, "Prompt", new IconLabel(GlyphsBuilder.create(MaterialDesignIconView.class)
                                .glyph(MaterialDesignIcon.ALERT_OCTAGON).size("2em").build(), "Java 1.8"))
                        .build());
        wizard.addStep(new WizardStepBuilder("Second step").addNumber("Age", 1, "Write your age").build());
        wizard.setOnWizardCompleted(e ->
        {
            System.out.println("Test: " + e.getSteps().get(0).getData().get("test").getValue() + " | "
                    + e.getSteps().get(0).getData().get("Enum").getValue());
        });
        wizard.showWizard();
    }
}