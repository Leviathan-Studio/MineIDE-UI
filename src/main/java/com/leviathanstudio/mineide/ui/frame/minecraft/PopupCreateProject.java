package com.leviathanstudio.mineide.ui.frame.minecraft;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.ui.controls.MDRoundButton;
import com.leviathanstudio.mineide.utils.Utils;

import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupCreateProject extends Gui
{
    private static int test;
    
    public static int getAuthorIndex()
    {
        return PopupCreateProject.test;
    }
    
    public static void setAuthorIndex(int testint)
    {
        PopupCreateProject.test = testint;
    }
    
    public static void init()
    {
        BorderPane root = new BorderPane();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("New Project - mcmod.info");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(MineIDE.primaryStage);
        stage.getIcons().add(new Image(Utils.IMG_DIR + "icon.png"));
        
        scene.getStylesheets().add(MineIDE.class.getResource(Utils.CSS_DIR + "style.css").toString());
        
        Label projectName = new Label("Nom: ");
        Label projectVersion = new Label("Version: ");
        Label projectDescription = new Label("Description: ");
        Label projectAuthors = new Label("Auteur(s): ");
        
        JFXTextField projectNameTextField = new JFXTextField();
        projectNameTextField.setPromptText("The name of your future mod !");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class).glyph(FontAwesomeIcon.WARNING).size("1em").styleClass("error").build());
        projectNameTextField.getValidators().add(validator);
        projectNameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if(!newVal)
                projectNameTextField.validate();
        });
        JFXTextField projectVerTextField = new JFXTextField("0.0.1");
        projectVerTextField.setPromptText("The version of your mod, like 0.0.1");
        projectVerTextField.getValidators().add(validator);
        projectVerTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if(!newVal)
                projectVerTextField.validate();
        });
        JFXListView<JFXTextField> list;
        
        list = new JFXListView<>();
        JFXTextField authorEmpty = new JFXTextField();
        authorEmpty.setPromptText("Author Name");
        list.getItems().add(authorEmpty);
        list.setPrefHeight(200);
        list.setEditable(true);
        
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.setOnEditCommit(new EventHandler<ListView.EditEvent<JFXTextField>>()
        {
            @Override
            public void handle(ListView.EditEvent<JFXTextField> t)
            {
                setAuthorIndex(t.getIndex());
                list.getItems().set(t.getIndex(), t.getNewValue());
            }
        });
        
        Image addButtonIcon = new Image(Utils.IMG_DIR + "/addIcon.png");
        Image removeButtonIcon = new Image(Utils.IMG_DIR + "/trashBinIcon.png");
        
        MDRoundButton addAuthorButton = new MDRoundButton();
        addAuthorButton.setGraphic(new ImageView(addButtonIcon));
        addAuthorButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                JFXTextField authorEmpty = new JFXTextField();
                authorEmpty.setPromptText("Author Name");
                list.getItems().add(authorEmpty);
            }
        });
        
        MDRoundButton removeAuthorButton = new MDRoundButton();
        removeAuthorButton.setGraphic(new ImageView(removeButtonIcon));
        removeAuthorButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    list.getItems().remove(list.getItems().get(getAuthorIndex()));
                }
                catch(Exception e)
                {}
            }
        });
        
        VBox vboxAddButton = new VBox(50);
        vboxAddButton.setAlignment(Pos.BOTTOM_RIGHT);
        vboxAddButton.getChildren().add(addAuthorButton);
        VBox vboxRemoveButton = new VBox(50);
        vboxRemoveButton.setAlignment(Pos.BOTTOM_RIGHT);
        vboxRemoveButton.getChildren().add(removeAuthorButton);
        
        JFXTextArea projectDescTextField = new JFXTextArea();
        projectDescTextField.setPromptText("The description for your mod !");
        
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
        grid.add(list, 2, 3);
        grid.add(vboxAddButton, 3, 3);
        grid.add(vboxRemoveButton, 4, 3);
        
        grid.add(projectDescription, 1, 5);
        grid.add(projectDescTextField, 2, 5);
        
        JFXButton nextButton = new JFXButton("Next");
        nextButton.setPrefSize(80, 30);
        nextButton.setStyle("-fx-button-type: RAISED;-fx-background-color: #4CAF50;-fx-text-fill: WHITE;-fx-font-size: 18px;");
        nextButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                JsonWriter writer;
                try
                {
                    Utils.FORGE_DIR.mkdirs();
                    writer = new JsonWriter(new FileWriter(Utils.FORGE_DIR + "/info_mod.json"));
                    
                    writer.beginObject();
                    writer.name("modName").value(projectNameTextField.getText());
                    writer.name("modModID").value(projectNameTextField.getText().toLowerCase().replace(" ", "_").replace("-", "_"));
                    writer.name("modVersion").value(projectVerTextField.getText());
                    
                    writer.name("authors");
                    writer.beginArray();
                    
                    writer.endArray();
                    
                    writer.name("modDescription").value(projectDescTextField.getText());
                    
                    writer.endObject();
                    writer.close();
                    
                    System.out.println("Done");
                    
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                
            }
        });
        
        VBox vboxNextButton = new VBox(50);
        vboxNextButton.setAlignment(Pos.BOTTOM_RIGHT);
        vboxNextButton.getChildren().add(nextButton);
        grid.add(vboxNextButton, 2, 8);
        
        root.setCenter(grid);
        stage.setScene(scene);
        
        stage.show();
    }
}