package com.leviathanstudio.mineide.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    
    public static final String IMG_DIR = ("/mineIDE/img/");
    public static final String CSS_DIR = ("/mineIDE/css/");
    public static final String JSON_DIR = ("./mineIDE/json/");
    public static final String HTML_DIR = ("/mineIDE/html/");
    public static final String LANG_DIR = ("/mineIDE/lang/");
    public static final String TEMPLATE_DIR = ("/minecraft/template/");
    
    public static final File ROOT = OSHelper.getWorkingDirectory();
    public static final File CONFIG = new File(ROOT, "config");
    public static final File PROJECT = new File(ROOT, "project");
    public static final File FORGE_DIR = new File(ROOT + "/workspace/forge/");
    
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
    
    public static void checkDir(File file)
    {
        if(!file.exists())
        {
            file.mkdirs();
        }
    }
    
    public static void replaceMappingSelected()
    {
        try
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String actualDate = dateFormat.format(date);
            
            String inputFile = new File(Utils.FORGE_DIR + "/build.gradle").getAbsolutePath();
            BufferedReader file = new BufferedReader(new FileReader(inputFile));
            String line;
            String input = "";
            
            while((line = file.readLine()) != null)
                input += line + '\n';
                
            file.close();
            
            System.out.println(input);
            
            Pattern p = Pattern.compile("\\_(.*?)\\\"");
            Matcher m = p.matcher(input);
            while(m.find())
            {
                input = input.replace("mappings = \"snapshot_" + m.group(1) + "\"", "mappings = \"snapshot_" + actualDate + "\"");
            }
            
            FileOutputStream fileOut = new FileOutputStream(inputFile);
            fileOut.write(input.getBytes());
            fileOut.close();
            
        }
        catch(Exception e)
        {
            System.out.println("Problem reading file.");
            e.printStackTrace();
        }
    }
}