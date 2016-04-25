package fr.zeamateis.mineide.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Utils
{
    public static final File FORGE_DIR = new File(OSHelper.getWorkingDirectory().getAbsoluteFile() + "/Workspace/Forge");
    
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