package fr.zeamateis.mineide.forge;

import java.io.IOException;

import fr.zeamateis.mineide.ui.frame.popup.PopupForgeInstallation;
import fr.zeamateis.mineide.utils.BatchHelper;
import fr.zeamateis.mineide.utils.OSHelper;

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
            BatchHelper.initBatchFile();
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
            PopupForgeInstallation.showPopup();
        }
    }
}
