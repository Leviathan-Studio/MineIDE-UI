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
    
    public static ProcessBuilder runGradle(String command) throws IOException
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        processBuilder.command("cmd", "/C", "cd " + Utils.FORGE_DIR + " && " + command);
        processBuilder.inheritIO();
        processBuilder.start();
        return processBuilder;
    }
    
    public static void startInstallation() throws IOException
    {
        runGradle(MineIDEConfig.getForgeInstallCommand());
    }
    
    public static ProcessBuilder runCommand(String type) throws IOException
    {
        return runGradle("gradlew run" + type);
    }
    
    public static void changeMapping() throws IOException
    {
        Utils.replaceMappingSelected();
    }
    
    public static void compileToJar() throws IOException
    {
        runGradle(MineIDEConfig.getForgeBuildCommand());
    }
    
    public static boolean isFinishedSetup()
    {
        if(new File(Utils.FORGE_DIR + "/installation.txt").exists())
            return ForgeHelper.finishedSetup = true;
        else
            return ForgeHelper.finishedSetup = false;
    }
    
    public static void setFinishedSetup(boolean finished)
    {
        ForgeHelper.finishedSetup = finished;
    }
}