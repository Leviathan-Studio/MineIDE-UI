package com.leviathanstudio.mineide.utils;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonReader
{
    private JsonObject jsonObject;
    
    public JsonObject getJsonObject()
    {
        return jsonObject;
    }
    
    public void initReading(String filePath)
    {
        JsonParser parser = new JsonParser();
        InputStream in = this.getClass().getResourceAsStream(filePath);
        jsonObject = parser.parse(new InputStreamReader(in)).getAsJsonObject();
    }
}