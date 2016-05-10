package com.leviathanstudio.mineide.forge;

import java.io.IOException;

import com.leviathanstudio.mineide.ui.frame.popup.PopupForgeInstallation;
import com.leviathanstudio.mineide.utils.OSHelper;

public class ForgeWorkspace
{
    private static volatile ForgeWorkspace instance = null;

    public static final ForgeWorkspace getInstance()
    {
        if (ForgeWorkspace.instance == null)
        {
            synchronized (ForgeHelper.class)
            {
                if (ForgeWorkspace.instance == null)
                    ForgeWorkspace.instance = new ForgeWorkspace();
            }
        }
        return ForgeWorkspace.instance;
    }

    private ForgeWorkspace()
    {

    }

    public void forceUpdate()
    {
        if (!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();

        setupForge();
    }

    public void installWorkspace()
    {
        if (!OSHelper.getWorkingDirectory().exists())
            OSHelper.getWorkingDirectory().mkdir();

        if (!ForgeHelper.getInstance().isFinishedSetup())
            setupForge();
        else
        {
            System.out.println("Already Forge Installed");
            PopupForgeInstallation.showPopup();
        }
    }

    private void setupForge()
    {
        System.out.println("Downloading Forge...");
        ForgeDownloader.getInstance().initDownload();

        if (ForgeDownloader.getInstance().isDownloadTerminated())
        {
            System.out.println("Forge Downloaded");

            try
            {
                System.out.println("Change Mapping");
                ForgeHelper.getInstance().changeMapping();
                System.out.println("Start Forge Installation");
                ForgeHelper.getInstance().startInstallation();
                if (ForgeHelper.getInstance().isFinishedSetup())
                {
                    ForgeHelper.getInstance().finishedSetup();
                    System.out.println("Forge Setup Finished");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}