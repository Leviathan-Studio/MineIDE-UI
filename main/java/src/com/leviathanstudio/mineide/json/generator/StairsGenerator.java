package com.leviathanstudio.mineide.json.generator;

public class StairsGenerator extends JsonGenerator
{
    
    public StairsGenerator(String modid)
    {
        super(modid, "stairs");
    }
    
    @Override
    public void addPatternKeys()
    {
        
    }
    
    @Override
    public void addPatternFiles()
    {
        this.addBlockstate("test", "default");
    }
    
}
