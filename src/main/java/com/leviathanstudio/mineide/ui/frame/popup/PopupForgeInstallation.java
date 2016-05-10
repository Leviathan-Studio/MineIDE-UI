package com.leviathanstudio.mineide.ui.frame.popup;

import java.util.Optional;

import com.leviathanstudio.mineide.forge.ForgeWorkspace;
import com.leviathanstudio.mineide.main.Translation;
import com.leviathanstudio.mineide.ui.Gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class PopupForgeInstallation
{

    public static void showPopup()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(
                Translation.LANG.getTranslation("alert.forge.reinstall.header") + Gui.mineIdeInfo.getForgeVersion());
        alert.setContentText(Translation.LANG.getTranslation("alert.forge.reinstall.content.line1") + "\n"
                + Translation.LANG.getTranslation("alert.forge.reinstall.content.line2"));

        ButtonType buttonForceUpdate = new ButtonType(Translation.LANG.getTranslation("button.forceUpdate"));
        ButtonType buttonCancel = new ButtonType(Translation.LANG.getTranslation("button.cancel"),
                ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonForceUpdate, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonForceUpdate)
            ForgeWorkspace.getInstance().forceUpdate();
        else
        {
        }
    }
}
