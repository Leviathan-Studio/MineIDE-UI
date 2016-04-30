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
        file = new File(Utils.FORGE_DIR + "/" + className + ".java");
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
            bufferedWriter.write(getFileTemplateContent().replaceAll(templateWordsToReplace[0][0], templateWordsToReplace[1][0]).replaceAll(templateWordsToReplace[0][1], templateWordsToReplace[1][1]).replaceAll(templateWordsToReplace[0][2], templateWordsToReplace[1][2]).replaceAll(templateWordsToReplace[0][3], templateWordsToReplace[1][3]).replaceAll(templateWordsToReplace[0][4], templateWordsToReplace[1][4]).replaceAll(templateWordsToReplace[0][5], templateWordsToReplace[1][5]));
            bufferedWriter.close();
        }
        catch(IOException e)
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