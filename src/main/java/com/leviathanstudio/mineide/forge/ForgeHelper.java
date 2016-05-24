package com.leviathanstudio.mineide.forge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leviathanstudio.mineide.json.MineIDEConfig;
import com.leviathanstudio.mineide.util.Util;
import com.leviathanstudio.mineide.utils.Command;

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

        File file = new File(Util.FORGE_DIR + "/installation.txt");
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
        Command task = new Command("cd " + Util.FORGE_DIR + " && " + command);

        return task.execute(Util.FORGE_DIR);
    }

    public void startInstallation() throws IOException
    {
        this.runGradle(MineIDEConfig.getForgeInstallCommand());
    }

    public ProcessBuilder runCommand(String type) throws IOException
    {
        return this.runGradle("gradlew run" + type);
    }

    public void changeMapping()
    {
        try
        {
            File inputFile = new File(Util.FORGE_DIR + "/build.gradle");
            BufferedReader file = new BufferedReader(new FileReader(inputFile));
            String line;
            String input = "";

            while ((line = file.readLine()) != null)
                input += line + '\n';

            file.close();

            Pattern p = Pattern.compile("\\_(.*?)\\\"");
            Matcher m = p.matcher(input);
            while (m.find())
                input = input.replace("mappings = \"snapshot_" + m.group(1) + "\"",
                        "mappings = " + "\"" + MineIDEConfig.getMappingVersion() + "\"");

            FileWriter fileOut = new FileWriter(inputFile);
            fileOut.write(input);
            fileOut.close();

        } catch (IOException e)
        {
            System.out.println("Problem reading file.");
            e.printStackTrace();
        }
    }

    public void compileToJar() throws IOException
    {
        this.runGradle(MineIDEConfig.getForgeBuildCommand());
    }

    public boolean isFinishedSetup()
    {
        return this.finishedSetup = new File(Util.FORGE_DIR + "/installation.txt").exists();
    }

    public void setFinishedSetup(boolean finished)
    {
        this.finishedSetup = finished;
    }
}