package fr.zeamateis.mineide.json;

public class MineIDEInfo
{
    private static String appName, appVersion, forgeVersion, description, credits;
    
    public MineIDEInfo()
    {
        GsonReader gsonReader = new GsonReader();
        gsonReader.initReading("files/json/mineIDEInfo.json");
        appName = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("name").getAsString();
        description = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("description").getAsString();
        appVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("app").getAsJsonObject().get("version").getAsString();
        forgeVersion = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("forgeVersion").getAsString();
        credits = gsonReader.getJsonObject().get("mineIdeInfo").getAsJsonObject().get("credits").getAsString();
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
    
    public static String getCredits()
    {
        return credits;
    }
    
    public static String getDescription()
    {
        return description;
    }
    
}