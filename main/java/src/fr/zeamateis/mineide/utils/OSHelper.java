package fr.zeamateis.mineide.utils;

import java.io.File;

import fr.zeamateis.mineide.json.MineIDEInfo;

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
                workingDirectory = new File(userHome, "." + MineIDEInfo.getAppName() + "/");
                break;
            case WINDOWS:
                final String applicationData = System.getenv("APPDATA");
                final String folder = applicationData != null ? applicationData : userHome;
                
                workingDirectory = new File(folder, "." + MineIDEInfo.getAppName() + "/");
                break;
            case MACOS:
                workingDirectory = new File(userHome, "Library/Application Support/" + MineIDEInfo.getAppName());
                break;
            default:
                workingDirectory = new File(userHome, MineIDEInfo.getAppName() + "/");
        }
        
        return workingDirectory;
    }
}
