package fr.zeamateis.mineide.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import fr.zeamateis.mineide.main.MineIDE;

public class GsonReader
{
    private JsonReader jsonReader;
    
    private JsonObject jsonObject;
    
    public JsonObject getJsonObject()
    {
        return jsonObject;
    }
    
    private JsonParser parser = new JsonParser();
    
    public void initReading(String filePath)
    {
        try
        {
            jsonReader = new JsonReader(new FileReader(MineIDE.class.getClassLoader().getResource(filePath).getFile()));
            jsonObject = parser.parse(jsonReader).getAsJsonObject();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
}