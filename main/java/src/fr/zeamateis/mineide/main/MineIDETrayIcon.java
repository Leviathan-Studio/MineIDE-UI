package fr.zeamateis.mineide.main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.stage.Stage;

// Java 8 code
public class MineIDETrayIcon
{
    Stage stage = MineIDE.primaryStage;
    
    private Timer notificationTimer = new Timer();
    
    public void initTrayIcon()
    {
        Platform.setImplicitExit(false);
        SwingUtilities.invokeLater(this::addAppToTray);
    }
    
    /**
     * Sets up a system tray icon for the application.
     */
    private void addAppToTray()
    {
        try
        {
            Toolkit.getDefaultToolkit();
            
            if(!SystemTray.isSupported())
            {
                System.out.println("No system tray support, application exiting.");
                Platform.exit();
            }
            
            SystemTray tray = SystemTray.getSystemTray();
            Image image = ImageIO.read(MineIDE.class.getResource("/files/img/icon_16.png"));
            TrayIcon trayIcon = new TrayIcon(image);
            
            trayIcon.addActionListener(event -> Platform.runLater(this::showStage));
            
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(event -> {
                notificationTimer.cancel();
                Platform.exit();
                tray.remove(trayIcon);
            });
            
            final PopupMenu popup = new PopupMenu();
            // popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            
            notificationTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    SwingUtilities.invokeLater(() -> trayIcon.displayMessage("MineIDE", "Successfully Launched !", TrayIcon.MessageType.INFO));
                }
            }, 2_000);
            
            tray.add(trayIcon);
        }
        catch(AWTException | IOException e)
        {
            System.out.println("Unable to init system tray");
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the application stage and ensures that it is brought ot the front of all stages.
     */
    private void showStage()
    {
        if(stage != null)
        {
            stage.show();
            stage.toFront();
        }
    }
}