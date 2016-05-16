package com.leviathanstudio.mineide.forge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.leviathanstudio.mineide.json.MineIDEConfig;
import com.leviathanstudio.mineide.utils.Command;
import com.leviathanstudio.mineide.utils.Utils;

public class ForgeHelper
{
    private static volatile ForgeHelper instance = null;

    public static final ForgeHelper getInstance()
    {
        if (ForgeHelper.instance == null)
            synchronized (ForgeHelper.class)
            {
                if (ForgeHelper.instance == null)
                    ForgeHelper.instance = new ForgeHelper();
            }
        return ForgeHelper.instance;
    }

    private boolean finishedSetup;

    private ForgeHelper()
    {

    }

    public void finishedSetup()
    {
        String content = "Download & Installation complete";

        File file = new File(Utils.FORGE_DIR + "/installation.txt");
        try
        {
            if (file.exists())
            {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                this.setFinishedSetup(true);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ProcessBuilder runGradle(String command) throws IOException
    {
        Command task = new Command("cd " + Utils.FORGE_DIR + " && " + command);

        return task.execute(Utils.FORGE_DIR);
    }

    public void startInstallation() throws IOException
    {
        this.runGradle(MineIDEConfig.getForgeInstallCommand());
    }

    public ProcessBuilder runCommand(String type) throws IOException
    {
        return this.runGradle("gradlew run" + type);
    }

    public void changeMapping() throws IOException
    {
        Utils.replaceMappingSelected();
    }

    public void compileToJar() throws IOException
    {
        this.runGradle(MineIDEConfig.getForgeBuildCommand());
    }

    public boolean isFinishedSetup()
    {
        return this.finishedSetup = new File(Utils.FORGE_DIR + "/installation.txt").exists();
    }

    public void setFinishedSetup(boolean finished)
    {
        this.finishedSetup = finished;
    }
}