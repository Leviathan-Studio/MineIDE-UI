package com.leviathanstudio.mineide.ui.frame.minecraft;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.wizard.WizardDialog;
import com.leviathanstudio.mineide.ui.wizard.WizardStepBuilder;

public class PopupCreateProject
{
    public static void init()
    {
        WizardDialog wizard = new WizardDialog("Project Creation", Gui.root);
        wizard.addStep(new WizardStepBuilder("First").addString("test", "default value", "je suis un prompt")
                .addString("test2", "", "je suis un prompt").build());
        wizard.addStep(
                new WizardStepBuilder("Second step").addNumber("Age", 1, "Write your age").build());
        wizard.setOnWizardCompleted(e ->
        {
            System.out.println("Test: " + e.getSteps().get(0).getData().get("test").getValue());
        });
        wizard.showWizard();
    }
}