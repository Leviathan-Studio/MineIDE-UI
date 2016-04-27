package com.leviathanstudio.mineide.minecraft;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.leviathanstudio.mineide.utils.Utils;

public class JavaClassWriter
{
    
    public void initWritting(String className, String fileTemplateContent)
    {
        BufferedWriter bw = null;
        try
        {
            File file = new File(Utils.FORGE_DIR + "/" + className + ".java");
            
            do
            {
                file.delete();
            }
            while(file.exists());
            do
            {
                file.createNewFile();
            }
            while(!file.exists());
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            
            String test[][] = {{"#packageName", "#modId", "#packageProxy", "#className"}, {"PACKAGE", "MODID", "PROXY", "CLASSNAME"}};
            int i = 0, j = 0;
            
            for(i = 0; i < 2; i++)
            {
                for(j = 0; j < 4; j++)
                {
                    System.out.println("TEST: " + test[i][j].replaceAll(test[0][0], test[1][1]));
                }
                System.out.println("");
            }
            
            bw.write(fileTemplateContent.replaceAll(test[0][0], test[1][1]));
            
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}