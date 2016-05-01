package com.leviathanstudio.mineide.ui.frame.minecraft;

import java.io.IOException;

import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.minecraft.JavaClassWriter;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.utils.TemplateReader;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupCreateProject extends Gui
{
    
    public static void init()
    {
        Dialog<Object> newProjectDialog = new Dialog<Object>();
        newProjectDialog.setTitle("New Project - mcmod.info");
        newProjectDialog.setResizable(false);
        
        Stage stage = (Stage)newProjectDialog.getDialogPane().getScene().getWindow();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(MineIDE.primaryStage);
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        
        Label projectName = new Label("Nom: ");
        Label projectVersion = new Label("Version: ");
        Label projectDescription = new Label("Description: ");
        Label projectAuthors = new Label("Auteur(s): ");
        
        TextField projectNameTextField = new TextField();
        projectNameTextField.setTooltip(new Tooltip("Write the name of your future mod !"));
        TextField projectVerTextField = new TextField();
        projectVerTextField.setTooltip(new Tooltip("Write the version of your mod, like 0.1 by example"));
        TextField projectAuthorsTextField = new TextField();
        projectAuthorsTextField.setTooltip(new Tooltip("Write the author(s) of your mod\nseparate it by comma if your not alone"));
        TextArea projectDescTextField = new TextArea();
        projectDescTextField.setTooltip(new Tooltip("Write a description for your mod !"));
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(projectName, 1, 1);
        grid.add(projectNameTextField, 2, 1);
        grid.add(projectVersion, 1, 2);
        grid.add(projectVerTextField, 2, 2);
        grid.add(projectAuthors, 1, 3);
        grid.add(projectAuthorsTextField, 2, 3);
        grid.add(projectDescription, 1, 4);
        grid.add(projectDescTextField, 2, 4);
        
        Button btn = new Button("Next");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                TemplateReader tmpltReader = new TemplateReader();
                tmpltReader.initReading("java", "main/mcmod");
                
                try
                {
                    JavaClassWriter.setClassName("test.json");
                    JavaClassWriter.setFileTemplate(tmpltReader.getOutputContent());
                    JavaClassWriter.write(new String[][] {{"#modId", "#modName", "#modDescription", "#modVersion"}, {projectNameTextField.getText().toLowerCase().replaceAll(" ", "_").replaceAll("-", "_"), projectNameTextField.getText(), projectDescTextField.getText(), projectVerTextField.getText()}});
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
        
        VBox hbBtn = new VBox(50);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 8);
        
        newProjectDialog.getDialogPane().setContent(grid);
        
        stage.show();
    }
}