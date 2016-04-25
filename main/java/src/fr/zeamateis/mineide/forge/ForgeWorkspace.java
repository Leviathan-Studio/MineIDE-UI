package fr.zeamateis.mineide.forge;

import java.io.IOException;

import fr.zeamateis.mineide.json.MineIDEInfo;
import fr.zeamateis.mineide.utils.OSHelper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ForgeWorkspace
{
    
    public static void initialization()
    {
        installWorkspace();
    }
    
    public static void forgeUpdate()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
            
        try
        {
            System.out.println("Downloading Forge...");
            ForgeDownloader.initDownload();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(ForgeDownloader.isDownloadTerminated())
            {
                System.out.println("Forge Downloaded");
                try
                {
                    System.out.println("Start Forge Installation");
                    ForgeHelper.startInstallation();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    ForgeHelper.finishedSetup();
                    System.out.println("Forge Setup Finished");
                }
            }
        }
    }
    
    private static void installWorkspace()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
        if(!ForgeHelper.isFinishedSetup())
        {
            try
            {
                System.out.println("Downloading Forge...");
                ForgeDownloader.initDownload();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(ForgeDownloader.isDownloadTerminated())
                {
                    System.out.println("Forge Downloaded");
                    try
                    {
                        System.out.println("Start Forge Installation");
                        ForgeHelper.startInstallation();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        ForgeHelper.finishedSetup();
                        System.out.println("Forge Setup Finished");
                    }
                }
            }
        }
        else
        {
            System.out.println("Already Forge Installed");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Hey ! You have already installed Forge " + MineIDEInfo.getForgeVersion());
            alert.setContentText("If you want to reinstall or change forge version,\nPlease click \"Force Forge Update\" in the Forge Menu");
            alert.showAndWait();
        }
    }
}
