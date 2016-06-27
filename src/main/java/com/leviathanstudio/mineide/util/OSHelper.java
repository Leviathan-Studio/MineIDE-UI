package com.leviathanstudio.mineide.util;

import java.io.File;

public class OSHelper
{
    public static enum OS
    {
        WINDOWS, MACOS, SOLARIS, LINUX, UNKNOWN;
    }

    public static OS getPlatform()
    {
        final String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win"))
            return OS.WINDOWS;
        if (osName.contains("mac"))
            return OS.MACOS;
        if (osName.contains("linux"))
            return OS.LINUX;
        if (osName.contains("unix"))
            return OS.LINUX;
        return OS.UNKNOWN;
    }

    public static File getWorkingDirectory()
    {
        final String userHome = System.getProperty("user.home", ".");
        File workingDirectory;
        switch (getPlatform())
        {
            case SOLARIS:
            case LINUX:
                workingDirectory = new File(userHome, "." + Util.APP_NAME + "/");
                break;
            case WINDOWS:
                final String applicationData = System.getenv("APPDATA");
                final String folder = applicationData != null ? applicationData : userHome;

                workingDirectory = new File(folder, "." + Util.APP_NAME + "/");
                break;
            case MACOS:
                workingDirectory = new File(userHome, "Library/Application Support/" + Util.APP_NAME);
                break;
            default:
                workingDirectory = new File(userHome, Util.APP_NAME + "/");
        }
        return workingDirectory;
    }
}
