package com.leviathanstudio.mineide.json;

import java.net.MalformedURLException;
import java.net.URL;

import com.leviathanstudio.mineide.util.Util;
import com.leviathanstudio.mineide.utils.GsonReader;

public class MineIDEConfig
{
    // MineIDEInfo
    private static String appName, appVersion, description;

    // Forge
    private static String forgeVersion, mappingVersion, forgeInstallCommand, forgeBuildCommand;
    private static URL    forgeDownloadLink, forgeVersionLink;

    public MineIDEConfig()
    {
        GsonReader gsonReader = new GsonReader();
        gsonReader.initReading(Util.JSON_DIR + "mineIDEConfig.json");
        // MineIDE Info
        MineIDEConfig.appName = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app")
                .getAsJsonObject().get("name").getAsString();
        MineIDEConfig.description = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app")
                .getAsJsonObject().get("description").getAsString();
        MineIDEConfig.appVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app")
                .getAsJsonObject().get("version").getAsString();

        // Forge
        MineIDEConfig.forgeInstallCommand = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject()
                .get("forgeConfig").getAsJsonObject().get("forgeInstallCommad").getAsString();
        MineIDEConfig.forgeBuildCommand = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject()
                .get("forgeConfig").getAsJsonObject().get("forgeBuildCommand").getAsString();
        MineIDEConfig.forgeVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeConfig")
                .getAsJsonObject().get("forgeVersion").getAsString();
        MineIDEConfig.mappingVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject()
                .get("forgeConfig").getAsJsonObject().get("mappingVersion").getAsString();
        try
        {
            MineIDEConfig.forgeDownloadLink = new URL(gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject()
                    .get("forgeConfig").getAsJsonObject().get("forgeDLUrl").getAsString());
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        try
        {
            MineIDEConfig.forgeVersionLink = new URL(gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject()
                    .get("forgeConfig").getAsJsonObject().get("forgeVersionUrl").getAsString());
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    public String getAppName()
    {
        return MineIDEConfig.appName;
    }

    public String getAppVersion()
    {
        return MineIDEConfig.appVersion;
    }

    public String getForgeVersion()
    {
        return MineIDEConfig.forgeVersion;
    }

    public static String getMappingVersion()
    {
        return MineIDEConfig.mappingVersion;
    }

    public static String getDescription()
    {
        return MineIDEConfig.description;
    }

    public static URL getForgeDownloadLink()
    {
        return MineIDEConfig.forgeDownloadLink;
    }

    public static URL getForgeVersionLink()
    {
        return MineIDEConfig.forgeVersionLink;
    }

    public static String getForgeInstallCommand()
    {
        return MineIDEConfig.forgeInstallCommand;
    }

    public static String getForgeBuildCommand()
    {
        return MineIDEConfig.forgeBuildCommand;
    }
}