package fr.zeamateis.mineide.forge;

import java.io.IOException;

import fr.zeamateis.mineide.utils.Utils;

public class ForgeHelper
{
    
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
}