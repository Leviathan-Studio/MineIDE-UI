package com.leviathanstudio.mineide.utils;

import java.io.File;

import com.leviathanstudio.mineide.json.MineIDEConfig;

public class OSHelper
{
    public static enum OS
    {
        WINDOWS,
        MACOS,
        SOLARIS,
        LINUX,
        UNKNOWN;
    }
    
    public static OS getPlatform()
    {
        final String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("win"))
            return OS.WINDOWS;
        if(osName.contains("mac"))
            return OS.MACOS;
        if(osName.contains("linux"))
            return OS.LINUX;
        if(osName.contains("unix"))
            return OS.LINUX;
        return OS.UNKNOWN;
    }
    
    public static File getWorkingDirectory()
    {
        final String userHome = System.getProperty("user.home", ".");
        File workingDirectory;
        switch(getPlatform())
        {
            case SOLARIS:
            case LINUX:
                workingDirectory = new File(userHome, "." + MineIDEConfig.getAppName() + "/");
                break;
            case WINDOWS:
                final String applicationData = System.getenv("APPDATA");
                final String folder = applicationData != null ? applicationData : userHome;
                
                workingDirectory = new File(folder, "." + MineIDEConfig.getAppName() + "/");
                break;
            case MACOS:
                workingDirectory = new File(userHome, "Library/Application Support/" + MineIDEConfig.getAppName());
                break;
            default:
                workingDirectory = new File(userHome, MineIDEConfig.getAppName() + "/");
        }
        
        return workingDirectory;
    }
}
