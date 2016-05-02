package com.leviathanstudio.mineide.forge;

import java.io.IOException;

import com.leviathanstudio.mineide.ui.frame.popup.PopupForgeInstallation;
import com.leviathanstudio.mineide.utils.OSHelper;

public class ForgeWorkspace
{
    public static void forceUpdate()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
        
        setupForge();
    }
    
    public static void installWorkspace()
    {
        if(!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();
        
        if(!ForgeHelper.isFinishedSetup())
        {
            setupForge();
        }
        else
        {
            System.out.println("Already Forge Installed");
            PopupForgeInstallation.showPopup();
        }
    }
    
    private static void setupForge()
    {
        System.out.println("Downloading Forge...");
        ForgeDownloader.initDownload();
        
        if(ForgeDownloader.isDownloadTerminated())
        {
            System.out.println("Forge Downloaded");
            
            try
            {
                System.out.println("Change Mapping");
                ForgeHelper.changeMapping();
                System.out.println("Start Forge Installation");
                ForgeHelper.startInstallation();
                if(ForgeHelper.isFinishedSetup())
                {
                    ForgeHelper.finishedSetup();
                    System.out.println("Forge Setup Finished");
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
    }
}