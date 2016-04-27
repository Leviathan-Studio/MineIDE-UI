package com.leviathanstudio.mineide.forge;

import java.io.IOException;

import com.leviathanstudio.mineide.ui.frame.popup.PopupForgeInstallation;
import com.leviathanstudio.mineide.utils.BatchHelper;
import com.leviathanstudio.mineide.utils.OSHelper;

public class ForgeWorkspace
{
    
    public static void forceUpdate()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
            
        BatchHelper.initBatchFile();
        
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
    
    public static void installWorkspace()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
            
        BatchHelper.initBatchFile();
        
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
