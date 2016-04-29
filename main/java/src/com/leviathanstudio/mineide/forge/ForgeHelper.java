package com.leviathanstudio.mineide.forge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.leviathanstudio.mineide.json.MineIDEConfig;
import com.leviathanstudio.mineide.utils.Utils;

public class ForgeHelper
{
    @SuppressWarnings("unused")
    private static boolean finishedSetup;
    
    public static void finishedSetup()
    {
        String content = "Download & Installation complete";
        
        File file = new File(Utils.FORGE_DIR + "/installation.txt");
        try
        {
            if(file.exists())
            {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                setFinishedSetup(true);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void startInstallation() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder().command("cmd", "/C", "cd " + Utils.FORGE_DIR + " & " + MineIDEConfig.getForgeInstallCommand());
        processBuilder.start();
    }
    
    public static void changeMapping() throws IOException
    {
        Utils.replaceMappingSelected();
    }
    
    public static void compileToJar() throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder().command("cmd", "/C", "cd " + Utils.FORGE_DIR + " & " + MineIDEConfig.getForgeBuildCommand());
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