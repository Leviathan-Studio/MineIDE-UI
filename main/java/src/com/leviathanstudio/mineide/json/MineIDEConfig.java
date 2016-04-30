package com.leviathanstudio.mineide.json;

import java.net.MalformedURLException;
import java.net.URL;

import com.leviathanstudio.mineide.utils.GsonReader;
import com.leviathanstudio.mineide.utils.Utils;

public class MineIDEConfig
{
    // MineIDEInfo
    private static String appName, appVersion, description;
    
    // Forge
    private static String forgeVersion, mappingVersion, forgeInstallCommand, forgeBuildCommand;
    private static URL forgeDownloadLink, forgeVersionLink;
    
    public MineIDEConfig()
    {
        GsonReader gsonReader = new GsonReader();
        gsonReader.initReading(Utils.JSON_DIR + "mineIDEConfig.json");
        // MineIDE Info
        appName = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("name").getAsString();
        description = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("description").getAsString();
        appVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("version").getAsString();
        
        // Forge
        forgeInstallCommand = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("forgeInstallCommad").getAsString();
        forgeBuildCommand = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("forgeBuildCommand").getAsString();
        forgeVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("forgeVersion").getAsString();
        mappingVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("mappingVersion").getAsString();
        try
        {
            forgeDownloadLink = new URL(gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("forgeDLUrl").getAsString());
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            forgeVersionLink = new URL(gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig").getAsJsonObject().get("forgeVersionUrl").getAsString());
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
    
    public String getAppName()
    {
        return appName;
    }
    
    public String getAppVersion()
    {
        return appVersion;
    }
    
    public String getForgeVersion()
    {
        return forgeVersion;
    }
    
    public static String getMappingVersion()
    {
        return mappingVersion;
    }
    
    public static String getDescription()
    {
        return description;
    }
    
    public static URL getForgeDownloadLink()
    {
        return forgeDownloadLink;
    }
    
    public static URL getForgeVersionLink()
    {
        return forgeVersionLink;
    }
    
    public static String getForgeInstallCommand()
    {
        return forgeInstallCommand;
    }
    
    public static String getForgeBuildCommand()
    {
        return forgeBuildCommand;
    }
}