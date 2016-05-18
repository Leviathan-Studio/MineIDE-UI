package com.leviathanstudio.mineide.ui.frame.minecraft;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import com.leviathanstudio.mineide.ui.GuiMain;
import com.leviathanstudio.mineide.ui.wizard.WizardDialog;
import com.leviathanstudio.mineide.ui.wizard.WizardStepBuilder;
import com.leviathanstudio.mineide.utils.Util;

import org.apache.logging.log4j.core.util.IOUtils;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;

public class PopupCreateProject
{
    public static void init()
    {
        WizardDialog wizard = new WizardDialog("Project Creation", GuiMain.root);
        wizard.addStep(new WizardStepBuilder().addStep("mcmod.info").addString("Name", "", "Your mod name")
                .addString("Version", "0.0.1", "The initial version of your mod")
                .addStringList("Authors", "Author Name").addBigString("Description", "", "The description of your mod")
                .build());
        wizard.setOnWizardCompleted(event ->
        {
            JsonWriter writer;
            try
            {
                Util.FORGE_DIR.mkdirs();
                writer = new JsonWriter(new FileWriter(Util.FORGE_DIR + "/info_mod.json"));
                writer.beginObject();
                writer.name("modName").value((String) wizard.getSteps().get(0).getData().get("Name").getValue());
                writer.name("modModID").value(((String) wizard.getSteps().get(0).getData().get("Name").getValue())
                        .toLowerCase().replace(" ", "_").replace("-", "_"));
                writer.name("modVersion").value((String) wizard.getSteps().get(0).getData().get("Version").getValue());
                writer.name("authors");
                writer.beginArray();
                ((SimpleListProperty<SimpleStringProperty>) wizard.getSteps().get(0).getData().get("Authors")).get()
                        .forEach(author ->
                {
                    try
                    {
                        writer.value(author.getValue());
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
                writer.endArray();
                writer.name("modDescription")
                        .value((String) wizard.getSteps().get(0).getData().get("Description").getValue());
                writer.endObject();
                writer.close();
                System.out.println(IOUtils.toString(new FileReader(Util.FORGE_DIR + "/info_mod.json")));

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        wizard.showWizard();
    }
}