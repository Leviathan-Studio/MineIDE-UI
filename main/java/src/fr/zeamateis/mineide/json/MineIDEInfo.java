package fr.zeamateis.mineide.json;

import java.net.MalformedURLException;
import java.net.URL;

import fr.zeamateis.mineide.reader.GsonReader;

public class MineIDEInfo
{
    private static String appName, appVersion, forgeVersion, description;
    private static URL forgeDownloadLink;
    
    public MineIDEInfo()
    {
        GsonReader gsonReader = new GsonReader();
        gsonReader.initReading("files/json/mineIDEInfo.json");
        appName = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("name").getAsString();
        description = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("description").getAsString();
        appVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("version").getAsString();
        forgeVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeVersion").getAsString();
        try
        {
            forgeDownloadLink = new URL(gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeDLUrl").getAsString());
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getAppName()
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
    
    public static String getDescription()
    {
        return description;
    }
    
    public static URL getForgeDownloadLink()
    {
        return forgeDownloadLink;
    }
    
}