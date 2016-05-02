package com.leviathanstudio.mineide.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.leviathanstudio.mineide.main.MineIDE;

public class TemplateReader
{
    private String outputContent;
    
    public void initReading(String templateType, String templateName)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try
        {
            FileReader inputFile = new FileReader(MineIDE.class.getResource(Utils.TEMPLATE_DIR + templateType + "/" + templateName + ".mide").getFile());
            BufferedReader bufferedReader = new BufferedReader(inputFile);
            
            String str;
            while((str = bufferedReader.readLine()) != null)
            {
                contentBuilder.append(str + System.lineSeparator());
            }
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