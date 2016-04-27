package com.leviathanstudio.mineide.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlReader
{
    private String outputContent;
    
    public void initReading(String urlName)
    {
        URL url;
        try
        {
            url = new URL(urlName);
            URLConnection conn = url.openConnection();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String inputLine;
            while((inputLine = br.readLine()) != null)
            {
                this.setOutputContent(inputLine);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getOutputContent()
    {
        return outputContent;
    }
    
    public void setOutputContent(String outputContent)
    {
        this.outputContent = outputContent;
    }
}
