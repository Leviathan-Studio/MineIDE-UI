package com.leviathanstudio.mineide.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.leviathanstudio.mineide.main.MineIDE;

public class HtmlReader
{
    private String outputContent;
    
    public void initReading(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(MineIDE.class.getResource(filePath).getFile()));
            String str;
            while((str = in.readLine()) != null)
            {
                contentBuilder.append(str);
            }
            in.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        this.setOutputContent(contentBuilder.toString());
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