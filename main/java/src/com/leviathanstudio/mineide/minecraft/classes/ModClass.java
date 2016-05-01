package com.leviathanstudio.mineide.minecraft.classes;

import java.io.IOException;

import com.leviathanstudio.mineide.minecraft.JavaClassWriter;
import com.leviathanstudio.mineide.utils.TemplateReader;

public class ModClass
{
    
    public static void initClassCreation() throws IOException
    {
        TemplateReader tmpltReader = new TemplateReader();
        tmpltReader.initReading("java", "main/ModClass");
        
        JavaClassWriter.setClassName("test");
        JavaClassWriter.setFileTemplate(tmpltReader.getOutputContent());
        JavaClassWriter.write(new String[][] {{"#packageName", "#modId", "#packageProxy", "#className", "#modName", "#modVersion"}, {"PACKAGE", "MODID", "PROXY", "CLASSNAME", "MOD_NAME", "MOD_VERSION"}});
    }
}