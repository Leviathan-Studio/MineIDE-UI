package com.leviathanstudio.mineide.minecraft;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.leviathanstudio.mineide.utils.Utils;

public class JavaClassWriter
{
    static BufferedWriter bufferedWriter;
    static FileWriter fileWriter;
    static File file;
    static String fileTemplateContent;
    static JavaClassWriter writerInstance;
    
    public static void setClassName(String className) throws IOException
    {
        file = new File(Utils.FORGE_DIR + "/" + className);
        do
        {
            file.delete();
        }
        while(file.exists());
        do
        {
            file.createNewFile();
        }
        while(!file.exists());
        fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        bufferedWriter = new BufferedWriter(fileWriter);
    }
    
    public static void write(String[][] templateWordsToReplace) throws IOException
    {
        try
        {
            // bufferedWriter.write(getFileTemplateContent().replace(templateWordsToReplace[0][j], templateWordsToReplace[1][j]));
            // bufferedWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private static String getFileTemplateContent()
    {
        return fileTemplateContent;
    }
    
    public static void setFileTemplate(String content)
    {
        fileTemplateContent = content;
    }
}