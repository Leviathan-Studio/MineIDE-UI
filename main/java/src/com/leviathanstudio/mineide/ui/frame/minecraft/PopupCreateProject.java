package com.leviathanstudio.mineide.ui.frame.minecraft;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import com.leviathanstudio.mineide.main.MineIDE;
import com.leviathanstudio.mineide.ui.Gui;
import com.leviathanstudio.mineide.utils.Utils;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldListCell;
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
        return test;
    }
    
    public static void setAuthorIndex(int testint)
    {
        test = testint;
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
        
        Label projectName = new Label("Nom: ");
        Label projectVersion = new Label("Version: ");
        Label projectDescription = new Label("Description: ");
        Label projectAuthors = new Label("Auteur(s): ");
        
        TextField projectNameTextField = new TextField();
        projectNameTextField.setTooltip(new Tooltip("Write the name of your future mod !"));
        TextField projectVerTextField = new TextField();
        projectVerTextField.setTooltip(new Tooltip("Write the version of your mod, like 0.1 by example"));
        // TextField projectAuthorsTextField = new TextField();
        // projectAuthorsTextField.setTooltip(new Tooltip("Write the author(s) of your mod\nseparate it by comma if your not alone"));
        ListView<String> list;
        
        list = new ListView<>(FXCollections.observableArrayList("Author Name"));
        list.setEditable(true);
        list.setPrefHeight(70);
        
        list.setCellFactory(TextFieldListCell.forListView());
        
        list.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>()
        {
            @Override
            public void handle(ListView.EditEvent<String> t)
            {
                setAuthorIndex(t.getIndex());
                list.getItems().set(t.getIndex(), t.getNewValue());
            }
        });
        
        Image addButtonIcon = new Image(Utils.IMG_DIR + "/addIcon.png");
        Image removeButtonIcon = new Image(Utils.IMG_DIR + "/trashBinIcon.png");
        
        Button addAuthorButton = new Button();
        addAuthorButton.setGraphic(new ImageView(addButtonIcon));
        addAuthorButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                list.getItems().add("Author Name");
            }
        });
        
        Button removeAuthorButton = new Button();
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
        grid.add(list, 2, 3);
        grid.add(vboxAddButton, 3, 3);
        grid.add(vboxRemoveButton, 4, 3);
        
        grid.add(projectDescription, 1, 5);
        grid.add(projectDescTextField, 2, 5);
        
        Button nextButton = new Button("Next");
        nextButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                JsonWriter writer;
                try
                {
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