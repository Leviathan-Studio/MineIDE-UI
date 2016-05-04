package com.leviathanstudio.mineide.ui.frame.minecraft;

import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.wizard.WizardDialog;
import com.leviathanstudio.mineide.ui.wizard.WizardStepBuilder;

public class PopupCreateProject
{
    public static void init()
    {
        WizardDialog wizard = new WizardDialog("Project Creation", Gui.root);
        wizard.addStep(new WizardStepBuilder().addString("test", "default value", "je suis un prompt")
                .addString("test2", "", "je suis un prompt").build());
        wizard.showWizard();
    }
}