package com.leviathanstudio.mineide.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leviathanstudio.mineide.json.MineIDEConfig;

public class Util
{
    public static final String IMG_DIR                = "/mineIDE/img/";
    public static final String CSS_DIR                = "/mineIDE/css/";
    public static final String JSON_DIR               = "/mineIDE/json/";
    public static final String HTML_DIR               = "/mineIDE/html/";
    public static final String LANG_DIR               = "/mineIDE/lang/";
    public static final String TEMPLATE_DIR           = "/minecraft/template/";

    public static final File   ROOT                   = OSHelper.getWorkingDirectory();
    public static final File   CONFIG                 = new File(Util.ROOT, "config");
    public static final File   PROJECT                = new File(Util.ROOT, "project");
    public static final File   LOG_DIR                = new File(Util.ROOT, "log/");
    public static final File   FORGE_DIR              = new File(Util.ROOT + "/workspace/forge");
    public static final File   FORGE_SRC_JAVA_DIR     = new File(Util.ROOT + "/workspace/forge/src/main/java");
    public static final File   FORGE_SRC_RESOURCE_DIR = new File(Util.ROOT + "/workspace/forge/src/main/resources");

    public static void writeFile(File filePath, String fileName, String fileContent, String fileExtension)
    {
        try
        {
            File file = new File(filePath + "/" + fileName + "." + fileExtension);

            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(fileContent);
            bw.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void initDirectory()
    {
        checkDir(ROOT);
        checkDir(CONFIG);
        checkDir(PROJECT);
        checkDir(LOG_DIR);
        checkDir(FORGE_DIR);
        checkDir(FORGE_SRC_JAVA_DIR);
        checkDir(FORGE_SRC_RESOURCE_DIR);
    }

    public static void checkDir(File file)
    {
        if (!file.exists())
            file.mkdirs();
    }

    public static void replaceMappingSelected()
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

        } catch (Exception e)
        {
            System.out.println("Problem reading file.");
            e.printStackTrace();
        }
    }
}