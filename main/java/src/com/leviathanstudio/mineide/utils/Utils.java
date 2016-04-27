package com.leviathanstudio.mineide.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Utils
{
    public static final File FORGE_DIR = new File(OSHelper.getWorkingDirectory().getAbsoluteFile() + "/Workspace/Forge");
    
    public static final String IMG_DIR = ("/mineIDE/img/");
    public static final String CSS_DIR = ("/mineIDE/css/");
    public static final String JSON_DIR = ("./mineIDE/json/");
    public static final String HTML_DIR = ("/mineIDE/html/");
    public static final String LANG_DIR = ("/mineIDE/lang/");
    public static final String TEMPLATE_DIR = ("/minecraft/template/");
    
    public static void writeFile(File filePath, String fileName, String fileContent, String fileExtension)
    {
        try
        {
            File file = new File(filePath + "/" + fileName + "." + fileExtension);
            
            if(!file.exists())
                file.createNewFile();
                
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileContent);
            bw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}