package com.leviathanstudio.mineide.forge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.leviathanstudio.mineide.utils.Utils;

public class ForgeHelper
{
    private static boolean finishedSetup;
    
    public static void finishedSetup()
    {
        String content = "Download & Installation complete";
        
        File file = new File(Utils.FORGE_DIR + "/installation.txt");
        try
        {
            if(!file.exists())
            {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            setFinishedSetup(true);
        }
    }
    
    public static void startInstallation() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder().command("cmd", "/C", "cd " + Utils.FORGE_DIR + " & start MineIDEInstall.bat");
        processBuilder.start();
    }
    
    public static void compileToJar() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder().command("cmd", "/C", "cd " + Utils.FORGE_DIR + " & start MineIDEBuild.bat");
        processBuilder.start();
    }
    
    public static boolean isFinishedSetup()
    {
        if(new File(Utils.FORGE_DIR + "/installation.txt").exists())
            return finishedSetup = true;
        else
            return finishedSetup = false;
    }
    
    public static void setFinishedSetup(boolean finished)
    {
        finishedSetup = finished;
    }
}